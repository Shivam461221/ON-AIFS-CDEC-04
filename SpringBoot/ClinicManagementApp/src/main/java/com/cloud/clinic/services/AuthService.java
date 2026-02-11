package com.cloud.clinic.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.clinic.dtos.AuthResponse;
import com.cloud.clinic.dtos.LoginRequest;
import com.cloud.clinic.dtos.RegisterRequest;
import com.cloud.clinic.dtos.UserResponseDto;
import com.cloud.clinic.entities.Role;
import com.cloud.clinic.entities.User;
import com.cloud.clinic.exception.ResourceAlreadyExistsException;
import com.cloud.clinic.exception.ResourceNotFoundException;
import com.cloud.clinic.exception.UnauthorizedException;
import com.cloud.clinic.repos.UserRepository;
import com.cloud.clinic.security.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        log.info("Registering new user with email: {}", request.getEmail());

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already registered: " + request.getEmail());
        }

        // Create new user
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        user.setIsActive(true);

        // For the first admin or self-registration, createdBy will be null
        // For other users, createdBy should be set by the creator (handled in controllers)
        
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getId());

        // Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole().name());

        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .message("User registered successfully")
                .build();
    }

    @Transactional
    public AuthResponse registerByUser(RegisterRequest request, User createdBy) {
        log.info("User {} is registering new user with email: {}", createdBy.getEmail(), request.getEmail());

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already registered: " + request.getEmail());
        }

        // Validate role-based registration permissions
        validateRegistrationPermissions(createdBy, request.getRole());

        // Create new user
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        user.setIsActive(true);
        user.setCreatedBy(createdBy);

        User savedUser = userRepository.save(user);
        log.info("User registered successfully by {} with ID: {}", createdBy.getEmail(), savedUser.getId());

        // Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole().name());

        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .message("User registered successfully")
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        log.info("Login attempt for email: {}", request.getEmail());

        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Get user details
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

            if (!user.getIsActive()) {
                throw new UnauthorizedException("User account is inactive");
            }

            // Generate JWT token
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

            log.info("User logged in successfully: {}", user.getEmail());

            return AuthResponse.builder()
                    .token(token)
                    .tokenType("Bearer")
                    .userId(user.getId())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .message("Login successful")
                    .build();

        } catch (Exception e) {
            log.error("Login failed for email: {}", request.getEmail());
            throw new UnauthorizedException("Invalid email or password");
        }
    }

    public UserResponseDto getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found"));

        return mapToUserResponseDto(user);
    }
    
    
    @Transactional
    public void changePassword(String email, String oldPassword, String newPassword) {
        log.info("Password change requested for: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Verify old password matches what is stored
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new UnauthorizedException("Current password is incorrect");
        }

        // Prevent reusing the same password
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new IllegalArgumentException("New password must be different from current password");
        }

        if (newPassword.length() < 6) {
            throw new IllegalArgumentException("New password must be at least 6 characters");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        log.info("Password changed successfully for: {}", email);
    }


    private void validateRegistrationPermissions(User createdBy, Role targetRole) {
        switch (createdBy.getRole()) {
            case ADMIN:
                // Admin can create any role
                break;
            case DOCTOR:
                // Doctor can only create RECEPTIONIST and cannot create ADMIN or DOCTOR
                if (targetRole == Role.ADMIN || targetRole == Role.DOCTOR) {
                    throw new UnauthorizedException("Doctors can only register Receptionists");
                }
                break;
            case RECEPTIONIST:
                // Receptionist cannot create users
                throw new UnauthorizedException("Receptionists cannot register users");
            default:
                throw new UnauthorizedException("Invalid role for user registration");
        }
    }

    private UserResponseDto mapToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())           // calls our custom builder method, unambiguous
                .isActive(user.getIsActive())
                .createdById(user.getCreatedBy() != null ? user.getCreatedBy().getId() : null)
                .createdByName(user.getCreatedBy() != null ? user.getCreatedBy().getFullName() : null)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
