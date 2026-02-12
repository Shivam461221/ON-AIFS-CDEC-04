package com.cloud.clinic.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.clinic.dtos.ApiResponse;
import com.cloud.clinic.dtos.AppointmentResponseDto;
import com.cloud.clinic.dtos.BookAppointmentRequest;
import com.cloud.clinic.dtos.UserResponseDto;
import com.cloud.clinic.entities.Role;
import com.cloud.clinic.services.AppointmentService;
import com.cloud.clinic.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/public")
// No @PreAuthorize — these endpoints are fully open (configured in SecurityConfig)
@RequiredArgsConstructor
public class PublicController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    /**
     * Book an appointment — no login required
     * Patient provides their details inline with the request
     * POST /api/public/appointments
     */
    @PostMapping("/appointments")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> bookAppointment(
            @Valid @RequestBody BookAppointmentRequest request) {

        AppointmentResponseDto appointment = appointmentService.bookAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Appointment booked successfully! Your appointment ID is: " + appointment.getId(),
                        appointment));
    }

    /**
     * Get all available (active) doctors — so patient can pick one when booking
     * GET /api/public/doctors
     */
    @GetMapping("/doctors")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAvailableDoctors() {
        List<UserResponseDto> doctors = userService.getAllByRole(Role.DOCTOR);
        return ResponseEntity.ok(ApiResponse.success("Available doctors fetched successfully", doctors));
    }

    /**
     * Get a doctor's booked slots on a specific date
     * so the patient can avoid already-taken times
     * GET /api/public/doctors/{doctorId}/slots?date=2025-06-15
     */
    @GetMapping("/doctors/{doctorId}/slots")
    public ResponseEntity<ApiResponse<List<AppointmentResponseDto>>> getDoctorBookedSlots(
            @PathVariable Long doctorId,
            @RequestParam String date) {

        List<AppointmentResponseDto> slots = appointmentService.getBookedSlotsForDoctor(doctorId, date);
        return ResponseEntity.ok(ApiResponse.success("Booked slots fetched successfully", slots));
    }
}	
