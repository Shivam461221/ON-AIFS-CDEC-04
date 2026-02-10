package com.cloud.clinic.dtos;

import java.time.LocalDateTime;

import com.cloud.clinic.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;

    @JsonProperty("role")
    private Role userRole;   // renamed field to avoid clash with javax.management.relation.Role

    private Boolean isActive;
    private Long createdById;
    private String createdByName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Manual builder to avoid Lombok @Builder auto-resolving wrong Role type
    public static UserResponseDtoBuilder builder() {
        return new UserResponseDtoBuilder();
    }

    public static class UserResponseDtoBuilder {
        private Long id;
        private String fullName;
        private String email;
        private String phoneNumber;
        private Role userRole;
        private Boolean isActive;
        private Long createdById;
        private String createdByName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public UserResponseDtoBuilder id(Long id) { this.id = id; return this; }
        public UserResponseDtoBuilder fullName(String fullName) { this.fullName = fullName; return this; }
        public UserResponseDtoBuilder email(String email) { this.email = email; return this; }
        public UserResponseDtoBuilder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public UserResponseDtoBuilder role(Role userRole) { this.userRole = userRole; return this; }  // still called .role() for clean call-site
        public UserResponseDtoBuilder isActive(Boolean isActive) { this.isActive = isActive; return this; }
        public UserResponseDtoBuilder createdById(Long createdById) { this.createdById = createdById; return this; }
        public UserResponseDtoBuilder createdByName(String createdByName) { this.createdByName = createdByName; return this; }
        public UserResponseDtoBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public UserResponseDtoBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public UserResponseDto build() {
            return new UserResponseDto(id, fullName, email, phoneNumber,
                    userRole, isActive, createdById, createdByName, createdAt, updatedAt);
        }
    }
}
