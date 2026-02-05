package com.cloud.clinic.dtos;

import com.cloud.clinic.entities.Role;
import com.cloud.clinic.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;
    private String tokenType = "Bearer";
    private Long userId;
    private String fullName;
    private String email;
    private Role role;
    private String message;

    // Constructor without message
    public AuthResponse(String token, Long userId, String fullName, String email,Role role) {
        this.token = token;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.tokenType = "Bearer";
    }
   }
