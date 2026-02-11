package com.cloud.clinic.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.cloud.clinic.entities.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private String medicalHistory;
    private Long registeredById;
    private String registeredByName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}