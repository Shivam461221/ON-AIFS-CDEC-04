package com.cloud.clinic.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloud.clinic.dtos.CreateUserRequest;
import com.cloud.clinic.dtos.UpdateUserRequest;
import com.cloud.clinic.dtos.UserResponseDto;
import com.cloud.clinic.entities.Role;
import com.cloud.clinic.entities.User;
import com.cloud.clinic.exception.ResourceAlreadyExistsException;
import com.cloud.clinic.exception.ResourceNotFoundException;
import com.cloud.clinic.exception.UnauthorizedException;
import com.cloud.clinic.repos.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ─────────────────────────────────────────────
    //  CREATE
    // ─────────────────────────────────────────────

    @Transactional
    public UserResponseDto createUser(CreateUserRequest request, User createdBy) {
        log.info("User [{}] creating new user with email: {}", createdBy.getEmail(), request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already in use: " + request.getEmail());
        }

        // Enforce who can create which role
        validateCreationPermission(createdBy.getRole(), request.getRole());

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        user.setIsActive(true);
        user.setCreatedBy(createdBy);

        User saved = userRepository.save(user);
        log.info("User created with ID: {} by [{}]", saved.getId(), createdBy.getEmail());
        return mapToDto(saved);
    }

    // ─────────────────────────────────────────────
    //  READ — Admin sees all, Doctor sees own
    // ─────────────────────────────────────────────

    @Transactional
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<UserResponseDto> getAllByRole(Role role) {
        return userRepository.findByRole(role)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<UserResponseDto> getUsersCreatedBy(User creator, Role role) {
        return userRepository.findByCreatedByAndRole(creator, role)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDto getUserById(Long id) {
        User user = findUserById(id);
        return mapToDto(user);
    }

    // ─────────────────────────────────────────────
    //  UPDATE
    // ─────────────────────────────────────────────

    @Transactional
    public UserResponseDto updateUser(Long id, UpdateUserRequest request, User updatedBy) {
        User user = findUserById(id);

        // Admin can update anyone; Doctor can only update users they created
        if (updatedBy.getRole() != Role.ADMIN) {
            ensureOwnership(user, updatedBy);
        }

        if (request.getFullName() != null)    user.setFullName(request.getFullName());
        if (request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new ResourceAlreadyExistsException("Email already in use: " + request.getEmail());
            }
            user.setEmail(request.getEmail());
        }

        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User saved = userRepository.save(user);
        log.info("User ID: {} updated by [{}]", id, updatedBy.getEmail());
        return mapToDto(saved);
    }

    // ─────────────────────────────────────────────
    //  DELETE (soft delete — sets isActive = false)
    // ─────────────────────────────────────────────

    @Transactional
    public void deactivateUser(Long id, User deletedBy) {
        User user = findUserById(id);

        // Admin can deactivate anyone; Doctor only their own created users
        if (deletedBy.getRole() != Role.ADMIN) {
            ensureOwnership(user, deletedBy);
        }

        // Prevent self-deactivation
        if (user.getId().equals(deletedBy.getId())) {
            throw new IllegalArgumentException("You cannot deactivate your own account");
        }

        user.setIsActive(false);
        userRepository.save(user);
        log.info("User ID: {} deactivated by [{}]", id, deletedBy.getEmail());
    }

    @Transactional
    public void reactivateUser(Long id, User reactivatedBy) {
        User user = findUserById(id);

        if (reactivatedBy.getRole() != Role.ADMIN) {
            throw new UnauthorizedException("Only admins can reactivate users");
        }

        user.setIsActive(true);
        userRepository.save(user);
        log.info("User ID: {} reactivated by [{}]", id, reactivatedBy.getEmail());
    }

    // ─────────────────────────────────────────────
    //  HELPERS
    // ─────────────────────────────────────────────

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    private void ensureOwnership(User target, User requester) {
        if (target.getCreatedBy() == null || !target.getCreatedBy().getId().equals(requester.getId())) {
            throw new UnauthorizedException("You do not have permission to manage this user");
        }
    }

    private void validateCreationPermission(Role creatorRole, Role targetRole) {
        switch (creatorRole) {
            case ADMIN:
                // Admin can create any role
                break;
            case DOCTOR:
                // Doctor can only create RECEPTIONIST
                if (targetRole != Role.RECEPTIONIST) {
                    throw new UnauthorizedException("Doctors can only create Receptionist accounts");
                }
                break;
            default:
                throw new UnauthorizedException("You do not have permission to create users");
        }
    }

    public UserResponseDto mapToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .createdById(user.getCreatedBy() != null ? user.getCreatedBy().getId() : null)
                .createdByName(user.getCreatedBy() != null ? user.getCreatedBy().getFullName() : null)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
