package com.cloud.clinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClinicalDetailsRequest {

    // All optional — only provided fields will be updated
    private String notes;
    private String diagnosis;
	private String prescription;
}
