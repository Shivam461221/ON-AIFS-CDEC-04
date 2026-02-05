package com.cloud.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.clinic.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
