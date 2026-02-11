package com.cloud.clinic.repos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cloud.clinic.entities.Appointment;
import com.cloud.clinic.entities.AppointmentStatus;
import com.cloud.clinic.entities.Patient;
import com.cloud.clinic.entities.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	 // All appointments for a specific doctor
    List<Appointment> findByDoctor(User doctor);

    // All appointments for a specific patient
    List<Appointment> findByPatient(Patient patient);

    // All appointments for a doctor on a specific date
    List<Appointment> findByDoctorAndAppointmentDate(User doctor, LocalDate date);

    // All appointments by status
    List<Appointment> findByStatus(AppointmentStatus status);

    // All appointments for a doctor filtered by status
    List<Appointment> findByDoctorAndStatus(User doctor, AppointmentStatus status);

    // Clash check — is the doctor already booked at the same date + time?
    boolean existsByDoctorAndAppointmentDateAndAppointmentTimeAndStatusNot(
            User doctor,
            LocalDate date,
            LocalTime time,
            AppointmentStatus status
    );

    // All appointments for a doctor ordered by date and time
    @Query("SELECT a FROM Appointment a WHERE a.doctor = :doctor " +
           "ORDER BY a.appointmentDate ASC, a.appointmentTime ASC")
    List<Appointment> findByDoctorOrderByDateTimeAsc(@Param("doctor") User doctor);
}
