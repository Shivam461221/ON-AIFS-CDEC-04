package com.cloud.clinic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cloud.clinic.dtos.AppointmentResponseDto;
import com.cloud.clinic.dtos.BookAppointmentRequest;
import com.cloud.clinic.entities.Appointment;
import com.cloud.clinic.entities.AppointmentStatus;
import com.cloud.clinic.entities.Gender;
import com.cloud.clinic.entities.Patient;
import com.cloud.clinic.entities.Role;
import com.cloud.clinic.entities.User;
import com.cloud.clinic.exception.ResourceNotFoundException;
import com.cloud.clinic.exception.UnauthorizedException;
import com.cloud.clinic.repos.AppointmentRepository;
import com.cloud.clinic.repos.PatientRepository;
import com.cloud.clinic.repos.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    // ─────────────────────────────────────────────
    //  BOOK — public, no login required
    //  Looks up patient by email; creates one if not found
    // ─────────────────────────────────────────────

    @Transactional
    public AppointmentResponseDto bookAppointment(BookAppointmentRequest request) {
        log.info("Public appointment booking for patient email: {}", request.getPatientEmail());

        // Resolve doctor — must exist and be active with DOCTOR role
        User doctor = userRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor not found with ID: " + request.getDoctorId()));

        if (doctor.getRole() != Role.DOCTOR || !doctor.getIsActive()) {
            throw new IllegalArgumentException("Selected doctor is not available");
        }

        // Check slot availability — reject if doctor already has a PENDING or CONFIRMED booking at same time
        boolean slotTaken = appointmentRepository
                .existsByDoctorAndAppointmentDateAndAppointmentTimeAndStatusNot(
                        doctor,
                        request.getAppointmentDate(),
                        request.getAppointmentTime(),
                        AppointmentStatus.CANCELLED
                );

        if (slotTaken) {
            throw new IllegalArgumentException(
                    "This time slot is already booked. Please choose a different time.");
        }

        // Resolve or auto-create patient by email
        Patient patient = patientRepository.findByEmail(request.getPatientEmail())
                .orElseGet(() -> {
                    Patient newPatient = new Patient();
                    newPatient.setFullName(request.getPatientName());
                    newPatient.setEmail(request.getPatientEmail());
                    newPatient.setPhoneNumber(request.getPatientPhone());
                    newPatient.setGender(Gender.OTHER); // default, can be updated later
                    // registeredBy is null for self-booked patients — that's intentional
                    return patientRepository.save(newPatient);
                });

        // Update name/phone in case they changed since last booking
        patient.setFullName(request.getPatientName());
        patient.setPhoneNumber(request.getPatientPhone());
        patientRepository.save(patient);

        // Create appointment
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setReasonForVisit(request.getReasonForVisit());
        appointment.setStatus(AppointmentStatus.PENDING);

        Appointment saved = appointmentRepository.save(appointment);
        log.info("Appointment booked with ID: {} for doctor ID: {}", saved.getId(), doctor.getId());
        return mapToDto(saved);
    }

    // ─────────────────────────────────────────────
    //  READ
    // ─────────────────────────────────────────────

    @Transactional
    public List<AppointmentResponseDto> getAllAppointments() {
        // Admin only
        return appointmentRepository.findAll()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Transactional
    public List<AppointmentResponseDto> getAppointmentsForDoctor(User doctor) {
        return appointmentRepository.findByDoctorOrderByDateTimeAsc(doctor)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Transactional
    public List<AppointmentResponseDto> getAppointmentsForPatient(Patient patient) {
        return appointmentRepository.findByPatient(patient)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Transactional
    public AppointmentResponseDto getAppointmentById(Long id, User requester) {
        Appointment appointment = findById(id);
        enforceReadAccess(appointment, requester);
        return mapToDto(appointment);
    }

    // ─────────────────────────────────────────────
    //  UPDATE STATUS — doctor updates appointment progress
    // ─────────────────────────────────────────────

    @Transactional
    public AppointmentResponseDto updateStatus(Long id, AppointmentStatus newStatus, User doctor) {
        Appointment appointment = findById(id);

        // Doctor can only update their own appointments
        if (!appointment.getDoctor().getId().equals(doctor.getId())) {
            throw new UnauthorizedException("You can only update your own appointments");
        }

        validateStatusTransition(appointment.getStatus(), newStatus);
        appointment.setStatus(newStatus);

        Appointment saved = appointmentRepository.save(appointment);
        log.info("Appointment ID: {} status updated to {} by doctor: {}",
                id, newStatus, doctor.getEmail());
        return mapToDto(saved);
    }

    // ─────────────────────────────────────────────
    //  UPDATE NOTES / DIAGNOSIS / PRESCRIPTION
    //  Doctor fills in clinical details post-visit
    // ─────────────────────────────────────────────

    @Transactional
    public AppointmentResponseDto updateClinicalDetails(
            Long id, String notes, String diagnosis, String prescription, User doctor) {

        Appointment appointment = findById(id);

        if (!appointment.getDoctor().getId().equals(doctor.getId())) {
            throw new UnauthorizedException("You can only update your own appointments");
        }

        if (appointment.getStatus() != AppointmentStatus.CONFIRMED &&
                appointment.getStatus() != AppointmentStatus.COMPLETED) {
            throw new IllegalArgumentException(
                    "Clinical details can only be added to CONFIRMED or COMPLETED appointments");
        }

        if (notes != null)        appointment.setNotes(notes);
        if (diagnosis != null)    appointment.setDiagnosis(diagnosis);
        if (prescription != null) appointment.setPrescription(prescription);

        Appointment saved = appointmentRepository.save(appointment);
        log.info("Clinical details updated for appointment ID: {} by doctor: {}", id, doctor.getEmail());
        return mapToDto(saved);
    }

    // ─────────────────────────────────────────────
    //  CANCEL
    // ─────────────────────────────────────────────

    @Transactional
    public AppointmentResponseDto cancelAppointment(Long id, User requester) {
        Appointment appointment = findById(id);

        // Admin can cancel any; Doctor can only cancel their own
        if (requester.getRole() != Role.ADMIN &&
                !appointment.getDoctor().getId().equals(requester.getId())) {
            throw new UnauthorizedException("You can only cancel your own appointments");
        }

        if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
            throw new IllegalArgumentException("Cannot cancel an already completed appointment");
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        Appointment saved = appointmentRepository.save(appointment);
        log.info("Appointment ID: {} cancelled by: {}", id, requester.getEmail());
        return mapToDto(saved);
    }

    // ─────────────────────────────────────────────
    //  HELPERS
    // ─────────────────────────────────────────────

    private Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
    }

    private void enforceReadAccess(Appointment appointment, User requester) {
        if (requester.getRole() == Role.ADMIN) return;
        if (!appointment.getDoctor().getId().equals(requester.getId())) {
            throw new UnauthorizedException("You do not have access to this appointment");
        }
    }

    private void validateStatusTransition(AppointmentStatus current, AppointmentStatus next) {
        // Allowed transitions:
        // PENDING     → CONFIRMED, CANCELLED
        // CONFIRMED   → COMPLETED, CANCELLED, NO_SHOW
        // COMPLETED   → (terminal — no further changes)
        // CANCELLED   → (terminal — no further changes)
        // NO_SHOW     → (terminal — no further changes)

        boolean valid = switch (current) {
            case PENDING    -> next == AppointmentStatus.CONFIRMED  || next == AppointmentStatus.CANCELLED;
            case CONFIRMED  -> next == AppointmentStatus.COMPLETED  ||
                               next == AppointmentStatus.CANCELLED  ||
                               next == AppointmentStatus.NO_SHOW;
            default         -> false;
        };

        if (!valid) {
            throw new IllegalArgumentException(
                    "Invalid status transition from " + current + " to " + next);
        }
    }

    public AppointmentResponseDto mapToDto(Appointment a) {
        return AppointmentResponseDto.builder()
                .id(a.getId())
                .patientId(a.getPatient().getId())
                .patientName(a.getPatient().getFullName())
                .patientEmail(a.getPatient().getEmail())
                .patientPhone(a.getPatient().getPhoneNumber())
                .doctorId(a.getDoctor().getId())
                .doctorName(a.getDoctor().getFullName())
                .doctorEmail(a.getDoctor().getEmail())
                .appointmentDate(a.getAppointmentDate())
                .appointmentTime(a.getAppointmentTime())
                .status(a.getStatus())
                .reasonForVisit(a.getReasonForVisit())
                .notes(a.getNotes())
                .diagnosis(a.getDiagnosis())
                .prescription(a.getPrescription())
                .createdAt(a.getCreatedAt())
                .updatedAt(a.getUpdatedAt())
                .build();
    }

    // ─────────────────────────────────────────────
    //  PUBLIC — booked slots for a doctor on a date
    //  Used by patients to avoid already taken times
    // ─────────────────────────────────────────────

    @Transactional
    public List<AppointmentResponseDto> getBookedSlotsForDoctor(Long doctorId, String dateStr) {
        User doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));

        if (doctor.getRole() != Role.DOCTOR) {
            throw new IllegalArgumentException("Provided ID does not belong to a doctor");
        }

        java.time.LocalDate date = java.time.LocalDate.parse(dateStr);

        return appointmentRepository.findByDoctorAndAppointmentDate(doctor, date)
                .stream()
                .filter(a -> a.getStatus() != AppointmentStatus.CANCELLED)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
