package com.cloud.clinic.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.clinic.dtos.ApiResponse;
import com.cloud.clinic.dtos.CreatePatientRequest;
import com.cloud.clinic.dtos.PatientResponseDto;
import com.cloud.clinic.dtos.UpdatePatientRequest;
import com.cloud.clinic.entities.User;
import com.cloud.clinic.services.PatientService;
import com.cloud.clinic.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/receptionist")
@PreAuthorize("hasRole('RECEPTIONIST')")   // entire controller locked to RECEPTIONIST
@RequiredArgsConstructor
public class ReceptionistController {

    private final UserService userService;
    private final PatientService patientService;

    // ════════════════════════════════════════════════
    //  PATIENT MANAGEMENT
    //  Receptionist can only manage patients they
    //  registered — no access to user management
    // ════════════════════════════════════════════════

    /**
     * Register a new Patient
     * POST /api/receptionist/patients
     */
    @PostMapping("/patients")
    public ResponseEntity<ApiResponse<PatientResponseDto>> createPatient(
            @Valid @RequestBody CreatePatientRequest request,
            Authentication authentication) {

        User receptionist = getLoggedInUser(authentication);
        PatientResponseDto created = patientService.createPatient(request, receptionist);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Patient registered successfully", created));
    }

    /**
     * Get all patients registered by this receptionist
     * GET /api/receptionist/patients
     */
    @GetMapping("/patients")
    public ResponseEntity<ApiResponse<List<PatientResponseDto>>> getMyPatients(
            Authentication authentication) {

        User receptionist = getLoggedInUser(authentication);
        List<PatientResponseDto> patients = patientService.getPatientsByRegistrar(receptionist);
        return ResponseEntity.ok(ApiResponse.success("Patients fetched successfully", patients));
    }

    /**
     * Get a single patient by ID (must be registered by this receptionist)
     * GET /api/receptionist/patients/{id}
     */
    @GetMapping("/patients/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> getPatientById(
            @PathVariable Long id,
            Authentication authentication) {

        User receptionist = getLoggedInUser(authentication);
        PatientResponseDto patient = patientService.getPatientById(id, receptionist);
        return ResponseEntity.ok(ApiResponse.success("Patient fetched successfully", patient));
    }

    /**
     * Update a patient's details (must be registered by this receptionist)
     * PUT /api/receptionist/patients/{id}
     */
    @PutMapping("/patients/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePatientRequest request,
            Authentication authentication) {

        User receptionist = getLoggedInUser(authentication);
        PatientResponseDto updated = patientService.updatePatient(id, request, receptionist);
        return ResponseEntity.ok(ApiResponse.success("Patient updated successfully", updated));
    }

    /**
     * Delete a patient (must be registered by this receptionist)
     * DELETE /api/receptionist/patients/{id}
     */
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(
            @PathVariable Long id,
            Authentication authentication) {

        User receptionist = getLoggedInUser(authentication);
        patientService.deletePatient(id, receptionist);
        return ResponseEntity.ok(ApiResponse.success("Patient deleted successfully"));
    }

    // ════════════════════════════════════════════════
    //  HELPER
    // ════════════════════════════════════════════════

    private User getLoggedInUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName());
    }
}