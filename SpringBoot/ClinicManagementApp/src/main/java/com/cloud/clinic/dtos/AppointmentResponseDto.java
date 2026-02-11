package com.cloud.clinic.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.cloud.clinic.entities.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDto {

    private Long id;

    // Patient info
    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientPhone;

    // Doctor info
    private Long doctorId;
    private String doctorName;
    private String doctorEmail;

    // Appointment info
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private AppointmentStatus status;
    private String reasonForVisit;
    private String notes;
    private String diagnosis;
    private String prescription;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
