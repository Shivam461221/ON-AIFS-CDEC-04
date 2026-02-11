package com.cloud.clinic.dtos;

import java.time.LocalDate;

import com.cloud.clinic.entities.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePatientRequest {

    // All fields optional — only provided fields will be updated

    private String fullName;

    @Email(message = "Please provide a valid email")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private Gender gender;

    private String address;

    private String medicalHistory;
}
