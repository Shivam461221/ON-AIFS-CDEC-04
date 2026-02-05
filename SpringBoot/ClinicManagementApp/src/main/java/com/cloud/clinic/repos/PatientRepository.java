package com.cloud.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.clinic.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
