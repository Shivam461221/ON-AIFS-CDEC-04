package com.cloud.clinic.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.clinic.entities.Role;
import com.cloud.clinic.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	List<User> findByCreatedByAndRole(User creator, Role role);

	Optional<User> findByRole(Role role);
}
