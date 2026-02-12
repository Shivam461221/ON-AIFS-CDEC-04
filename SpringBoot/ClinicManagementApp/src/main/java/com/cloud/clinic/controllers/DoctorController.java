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
import com.cloud.clinic.dtos.CreateUserRequest;
import com.cloud.clinic.dtos.PatientResponseDto;
import com.cloud.clinic.dtos.UpdatePatientRequest;
import com.cloud.clinic.dtos.UpdateUserRequest;
import com.cloud.clinic.dtos.UserResponseDto;
import com.cloud.clinic.entities.Role;
import com.cloud.clinic.entities.User;
import com.cloud.clinic.exception.UnauthorizedException;
import com.cloud.clinic.services.PatientService;
import com.cloud.clinic.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/doctor")
@PreAuthorize("hasRole('DOCTOR')")   // entire controller locked to DOCTOR
@RequiredArgsConstructor
public class DoctorController {

    private final UserService userService;
    private final PatientService patientService;

    // ════════════════════════════════════════════════
    //  RECEPTIONIST MANAGEMENT
    //  Doctor can create / view / update / deactivate
    //  only the receptionists they registered
    // ════════════════════════════════════════════════

    /**
     * Register a new Receptionist
     * POST /api/doctor/receptionists
     */
    @PostMapping("/receptionists")
    public ResponseEntity<ApiResponse<UserResponseDto>> createReceptionist(
            @Valid @RequestBody CreateUserRequest request,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);

        // Enforce: doctor can only create RECEPTIONIST role
        if (request.getRole() != Role.RECEPTIONIST) {
            throw new UnauthorizedException("Doctors can only register Receptionists");
        }

        UserResponseDto created = userService.createUser(request, doctor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Receptionist registered successfully", created));
    }

    /**
     * Get all receptionists registered by this doctor
     * GET /api/doctor/receptionists
     */
    @GetMapping("/receptionists")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getMyReceptionists(
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        List<UserResponseDto> receptionists = userService.getUsersCreatedBy(doctor, Role.RECEPTIONIST);
        System.out.println(receptionists);
        return ResponseEntity.ok(ApiResponse.success("Receptionists fetched successfully", receptionists));
    }

    /**
     * Get a single receptionist by ID (must be created by this doctor)
     * GET /api/doctor/receptionists/{id}
     */
    @GetMapping("/receptionists/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getReceptionistById(
            @PathVariable Long id,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        UserResponseDto receptionist = userService.getUserById(id);
        enforceUserOwnership(receptionist, doctor);
        return ResponseEntity.ok(ApiResponse.success("Receptionist fetched successfully", receptionist));
    }

    /**
     * Update a receptionist's details (must be created by this doctor)
     * PUT /api/doctor/receptionists/{id}
     */
    @PutMapping("/receptionists/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateReceptionist(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        UserResponseDto updated = userService.updateUser(id, request, doctor);
        return ResponseEntity.ok(ApiResponse.success("Receptionist updated successfully", updated));
    }

    /**
     * Deactivate a receptionist (must be created by this doctor)
     * DELETE /api/doctor/receptionists/{id}
     */
    @DeleteMapping("/receptionists/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateReceptionist(
            @PathVariable Long id,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        userService.deactivateUser(id, doctor);
        return ResponseEntity.ok(ApiResponse.success("Receptionist deactivated successfully"));
    }

    // ════════════════════════════════════════════════
    //  PATIENT MANAGEMENT
    //  Doctor can create / view / update / delete
    //  only the patients they registered
    // ════════════════════════════════════════════════

    /**
     * Register a new Patient
     * POST /api/doctor/patients
     */
    @PostMapping("/patients")
    public ResponseEntity<ApiResponse<PatientResponseDto>> createPatient(
            @Valid @RequestBody CreatePatientRequest request,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        PatientResponseDto created = patientService.createPatient(request, doctor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Patient registered successfully", created));
    }

    /**
     * Get all patients registered by this doctor
     * GET /api/doctor/patients
     */
    @GetMapping("/patients")
    public ResponseEntity<ApiResponse<List<PatientResponseDto>>> getMyPatients(
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        List<PatientResponseDto> patients = patientService.getPatientsByRegistrar(doctor);
        return ResponseEntity.ok(ApiResponse.success("Patients fetched successfully", patients));
    }

    /**
     * Get a single patient by ID (must be registered by this doctor)
     * GET /api/doctor/patients/{id}
     */
    @GetMapping("/patients/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> getPatientById(
            @PathVariable Long id,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        PatientResponseDto patient = patientService.getPatientById(id, doctor);
        return ResponseEntity.ok(ApiResponse.success("Patient fetched successfully", patient));
    }

    /**
     * Update a patient's details (must be registered by this doctor)
     * PUT /api/doctor/patients/{id}
     */
    @PutMapping("/patients/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePatientRequest request,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        PatientResponseDto updated = patientService.updatePatient(id, request, doctor);
        return ResponseEntity.ok(ApiResponse.success("Patient updated successfully", updated));
    }

    /**
     * Delete a patient (must be registered by this doctor)
     * DELETE /api/doctor/patients/{id}
     */
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(
            @PathVariable Long id,
            Authentication authentication) {

        User doctor = getLoggedInUser(authentication);
        patientService.deletePatient(id, doctor);
        return ResponseEntity.ok(ApiResponse.success("Patient deleted successfully"));
    }

    // ════════════════════════════════════════════════
    //  HELPERS
    // ════════════════════════════════════════════════

    private User getLoggedInUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName());
    }

    // Extra guard: ensures the fetched user was created by this doctor
    private void enforceUserOwnership(UserResponseDto target, User doctor) {
        if (target.getCreatedById() == null ||
                !target.getCreatedById().equals(doctor.getId())) {
            throw new UnauthorizedException("You do not have access to this user");
        }
    }
}
