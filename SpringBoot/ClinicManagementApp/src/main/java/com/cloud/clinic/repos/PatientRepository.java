package com.cloud.clinic.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.clinic.entities.Patient;
import com.cloud.clinic.entities.User;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	 // Find all patients registered by a specific user (Doctor or Receptionist)
    List<Patient> findByRegisteredBy(User registeredBy);

    // Check if a patient with email exists
    boolean existsByEmail(String email);

    // Find patient by email
    java.util.Optional<Patient> findByEmail(String email);

    // Find all patients registered by a specific user - used for scoped access checks
    boolean existsByIdAndRegisteredBy(Long id, User registeredBy);
}
