package com.cloud.clinic.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.clinic.dtos.ApiResponse;
import com.cloud.clinic.dtos.AppointmentResponseDto;
import com.cloud.clinic.dtos.UpdateAppointmentStatusRequest;
import com.cloud.clinic.dtos.UpdateClinicalDetailsRequest;
import com.cloud.clinic.entities.User;
import com.cloud.clinic.services.AppointmentService;
import com.cloud.clinic.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/doctor/appointments")
@PreAuthorize("hasRole('DOCTOR')")
@RequiredArgsConstructor
public class DoctorAppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    /**
     * Get all appointments assigned to this doctor
     * GET /api/doctor/appointments
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<AppointmentResponseDto>>> getMyAppointments(
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        List<AppointmentResponseDto> appointments =
                appointmentService.getAppointmentsForDoctor(doctor);
        return ResponseEntity.ok(ApiResponse.success("Appointments fetched successfully", appointments));
    }

    /**
     * Get a single appointment by ID
     * GET /api/doctor/appointments/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> getAppointmentById(
            @PathVariable Long id,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        AppointmentResponseDto appointment = appointmentService.getAppointmentById(id, doctor);
        return ResponseEntity.ok(ApiResponse.success("Appointment fetched successfully", appointment));
    }

    /**
     * Update appointment status
     * PENDING → CONFIRMED → COMPLETED / NO_SHOW / CANCELLED
     * PATCH /api/doctor/appointments/{id}/status
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAppointmentStatusRequest request,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        AppointmentResponseDto updated =
                appointmentService.updateStatus(id, request.getStatus(), doctor);
        return ResponseEntity.ok(ApiResponse.success("Appointment status updated successfully", updated));
    }

    /**
     * Add clinical details — notes, diagnosis, prescription
     * PATCH /api/doctor/appointments/{id}/clinical
     */
    @PatchMapping("/{id}/clinical")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> updateClinicalDetails(
            @PathVariable Long id,
            @RequestBody UpdateClinicalDetailsRequest request,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        AppointmentResponseDto updated = appointmentService.updateClinicalDetails(
                id,
                request.getNotes(),
                request.getDiagnosis(),
                request.getPrescription(),
                doctor);
        return ResponseEntity.ok(ApiResponse.success("Clinical details updated successfully", updated));
    }

    /**
     * Cancel an appointment
     * PATCH /api/doctor/appointments/{id}/cancel
     */
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> cancelAppointment(
            @PathVariable Long id,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        AppointmentResponseDto cancelled = appointmentService.cancelAppointment(id, doctor);
        return ResponseEntity.ok(ApiResponse.success("Appointment cancelled successfully", cancelled));
    }

    // ════════════════════════════════════════════════
    //  HELPER
    // ════════════════════════════════════════════════

    private User getLoggedInUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName());
    }
}
