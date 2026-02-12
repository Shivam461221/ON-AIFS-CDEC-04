package com.cloud.clinic.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.cloud.clinic.services.PatientService;
import com.cloud.clinic.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')") // entire controller locked to ADMIN
@RequiredArgsConstructor
public class AdminController {

	private final UserService userService;
	private final PatientService patientService;

	// ════════════════════════════════════════════════
	// USER MANAGEMENT
	// ════════════════════════════════════════════════

	/**
	 * Create any user — Admin, Doctor, or Receptionist POST /api/admin/users
	 */
	@PostMapping("/users")
	public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@Valid @RequestBody CreateUserRequest request,
			Authentication authentication) {

		User admin = getLoggedInUser(authentication);
		UserResponseDto created = userService.createUser(request, admin);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success("User created successfully", created));
	}

	/**
	 * Get all users in the system GET /api/admin/users
	 */
	@GetMapping("/users")
	public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
		List<UserResponseDto> users = userService.getAllUsers();
		return ResponseEntity.ok(ApiResponse.success("Users fetched successfully", users));
	}

	/**
	 * Get all users by role GET /api/admin/users?role=DOCTOR
	 */
	@GetMapping("/users/role/{role}")
	public ResponseEntity<ApiResponse<List<UserResponseDto>>> getUsersByRole(@PathVariable Role role) {
		List<UserResponseDto> users = userService.getAllByRole(role);
		return ResponseEntity.ok(ApiResponse.success("Users fetched successfully", users));
	}

	/**
	 * Get a single user by ID GET /api/admin/users/{id}
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
		UserResponseDto user = userService.getUserById(id);
		return ResponseEntity.ok(ApiResponse.success("User fetched successfully", user));
	}

	/**
	 * Update any user's details PUT /api/admin/users/{id}
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable Long id,
			@Valid @RequestBody UpdateUserRequest request, Authentication authentication) {

		User admin = getLoggedInUser(authentication);
		UserResponseDto updated = userService.updateUser(id, request, admin);
		return ResponseEntity.ok(ApiResponse.success("User updated successfully", updated));
	}

	/**
	 * Deactivate (soft-delete) a user DELETE /api/admin/users/{id}
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<ApiResponse<Void>> deactivateUser(@PathVariable Long id, Authentication authentication) {

		User admin = getLoggedInUser(authentication);
		userService.deactivateUser(id, admin);
		return ResponseEntity.ok(ApiResponse.success("User deactivated successfully"));
	}

	/**
	 * Reactivate a previously deactivated user PATCH
	 * /api/admin/users/{id}/reactivate
	 */
	@PatchMapping("/users/{id}/reactivate")
	public ResponseEntity<ApiResponse<Void>> reactivateUser(@PathVariable Long id, Authentication authentication) {

		User admin = getLoggedInUser(authentication);
		userService.reactivateUser(id, admin);
		return ResponseEntity.ok(ApiResponse.success("User reactivated successfully"));
	}

	// ════════════════════════════════════════════════
	// PATIENT MANAGEMENT
	// ════════════════════════════════════════════════

	/**
	 * Create a new patient POST /api/admin/patients
	 */
	@PostMapping("/patients")
	public ResponseEntity<ApiResponse<PatientResponseDto>> createPatient(
			@Valid @RequestBody CreatePatientRequest request, Authentication authentication) {

		User admin = getLoggedInUser(authentication);
		PatientResponseDto created = patientService.createPatient(request, admin);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success("Patient created successfully", created));
	}

	/**
	 * Get all patients in the system GET /api/admin/patients
	 */
	@GetMapping("/patients")
	public ResponseEntity<ApiResponse<List<PatientResponseDto>>> getAllPatients() {
		List<PatientResponseDto> patients = patientService.getAllPatients();
		return ResponseEntity.ok(ApiResponse.success("Patients fetched successfully", patients));
	}

	/**
	 * Get a single patient by ID GET /api/admin/patients/{id}
	 */
	@GetMapping("/patients/{id}")
	public ResponseEntity<ApiResponse<PatientResponseDto>> getPatientById(@PathVariable Long id,
			Authentication authentication) {

		User admin = getLoggedInUser(authentication);
		PatientResponseDto patient = patientService.getPatientById(id, admin);
		return ResponseEntity.ok(ApiResponse.success("Patient fetched successfully", patient));
	}

	/**
	 * Update a patient's details PUT /api/admin/patients/{id}
	 */
	@PutMapping("/patients/{id}")
	public ResponseEntity<ApiResponse<PatientResponseDto>> updatePatient(@PathVariable Long id,
			@Valid @RequestBody UpdatePatientRequest request, Authentication authentication) {

		User admin = getLoggedInUser(authentication);
		PatientResponseDto updated = patientService.updatePatient(id, request, admin);
		return ResponseEntity.ok(ApiResponse.success("Patient updated successfully", updated));
	}

	/**
	 * Delete a patient permanently DELETE /api/admin/patients/{id}
	 */
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<ApiResponse<Void>> deletePatient(@PathVariable Long id, Authentication authentication) {

		User admin = getLoggedInUser(authentication);
		patientService.deletePatient(id, admin);
		return ResponseEntity.ok(ApiResponse.success("Patient deleted successfully"));
	}

	// ════════════════════════════════════════════════
	// HELPER
	// ════════════════════════════════════════════════

	/**
	 * Resolves the currently logged-in User entity from the JWT principal. We never
	 * trust role/id from the request body — always from the token.
	 */
	private User getLoggedInUser(Authentication authentication) {
		return userService.findUserByEmail(authentication.getName());
	}
}
