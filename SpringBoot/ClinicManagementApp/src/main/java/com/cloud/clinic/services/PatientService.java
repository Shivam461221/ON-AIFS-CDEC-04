package com.cloud.clinic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cloud.clinic.dtos.CreatePatientRequest;
import com.cloud.clinic.dtos.PatientResponseDto;
import com.cloud.clinic.dtos.UpdatePatientRequest;
import com.cloud.clinic.entities.Patient;
import com.cloud.clinic.entities.Role;
import com.cloud.clinic.entities.User;
import com.cloud.clinic.exception.ResourceAlreadyExistsException;
import com.cloud.clinic.exception.ResourceNotFoundException;
import com.cloud.clinic.exception.UnauthorizedException;
import com.cloud.clinic.repos.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final PatientRepository patientRepository;

    // ─────────────────────────────────────────────
    //  CREATE
    // ─────────────────────────────────────────────

    @Transactional
    public PatientResponseDto createPatient(CreatePatientRequest request, User registeredBy) {
        log.info("User [{}] registering new patient: {}", registeredBy.getEmail(), request.getEmail());

        if (patientRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Patient already exists with email: " + request.getEmail());
        }

        Patient patient = new Patient();
        patient.setFullName(request.getFullName());
        patient.setEmail(request.getEmail());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setAddress(request.getAddress());
        patient.setMedicalHistory(request.getMedicalHistory());
        patient.setRegisteredBy(registeredBy);  // always set from JWT, never from request body

        Patient saved = patientRepository.save(patient);
        log.info("Patient created with ID: {} by [{}]", saved.getId(), registeredBy.getEmail());
        return mapToDto(saved);
    }

    // ─────────────────────────────────────────────
    //  READ
    // ─────────────────────────────────────────────

    @Transactional()
    public List<PatientResponseDto> getAllPatients() {
        // Admin only — sees every patient in the system
        return patientRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional()
    public List<PatientResponseDto> getPatientsByRegistrar(User registeredBy) {
        // Doctor / Receptionist — sees only their own registered patients
        return patientRepository.findByRegisteredBy(registeredBy)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional()
    public PatientResponseDto getPatientById(Long id, User requester) {
        Patient patient = findPatientById(id);
        enforceReadAccess(patient, requester);
        return mapToDto(patient);
    }

    // ─────────────────────────────────────────────
    //  UPDATE
    // ─────────────────────────────────────────────

    @Transactional
    public PatientResponseDto updatePatient(Long id, UpdatePatientRequest request, User updatedBy) {
        Patient patient = findPatientById(id);
        enforceWriteAccess(patient, updatedBy);

        if (request.getFullName() != null)    patient.setFullName(request.getFullName());
        if (request.getPhoneNumber() != null) patient.setPhoneNumber(request.getPhoneNumber());
        if (request.getDateOfBirth() != null) patient.setDateOfBirth(request.getDateOfBirth());
        if (request.getGender() != null)      patient.setGender(request.getGender());
        if (request.getAddress() != null)     patient.setAddress(request.getAddress());
        if (request.getMedicalHistory() != null) patient.setMedicalHistory(request.getMedicalHistory());

        if (request.getEmail() != null && !request.getEmail().equals(patient.getEmail())) {
            if (patientRepository.existsByEmail(request.getEmail())) {
                throw new ResourceAlreadyExistsException("Email already in use: " + request.getEmail());
            }
            patient.setEmail(request.getEmail());
        }

        Patient saved = patientRepository.save(patient);
        log.info("Patient ID: {} updated by [{}]", id, updatedBy.getEmail());
        return mapToDto(saved);
    }

    // ─────────────────────────────────────────────
    //  DELETE
    // ─────────────────────────────────────────────

    @Transactional
    public void deletePatient(Long id, User deletedBy) {
        Patient patient = findPatientById(id);
        enforceWriteAccess(patient, deletedBy);
        patientRepository.delete(patient);
        log.info("Patient ID: {} deleted by [{}]", id, deletedBy.getEmail());
    }

    // ─────────────────────────────────────────────
    //  HELPERS
    // ─────────────────────────────────────────────

    public Patient findPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
    }

    /**
     * Admin can read any patient.
     * Doctor/Receptionist can only read patients they registered.
     */
    private void enforceReadAccess(Patient patient, User requester) {
        if (requester.getRole() == Role.ADMIN) return;
        if (patient.getRegisteredBy() == null ||
                !patient.getRegisteredBy().getId().equals(requester.getId())) {
            throw new UnauthorizedException("You do not have access to this patient's data");
        }
    }

    /**
     * Admin can modify any patient.
     * Doctor/Receptionist can only modify patients they registered.
     */
    private void enforceWriteAccess(Patient patient, User requester) {
        if (requester.getRole() == Role.ADMIN) return;
        if (patient.getRegisteredBy() == null ||
                !patient.getRegisteredBy().getId().equals(requester.getId())) {
            throw new UnauthorizedException("You do not have permission to modify this patient");
        }
    }

    public PatientResponseDto mapToDto(Patient patient) {
        return PatientResponseDto.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .email(patient.getEmail())
                .phoneNumber(patient.getPhoneNumber())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .address(patient.getAddress())
                .medicalHistory(patient.getMedicalHistory())
                .registeredById(patient.getRegisteredBy() != null ? patient.getRegisteredBy().getId() : null)
                .registeredByName(patient.getRegisteredBy() != null ? patient.getRegisteredBy().getFullName() : null)
                .createdAt(patient.getCreatedAt())
                .updatedAt(patient.getUpdatedAt())
                .build();
    }
}
