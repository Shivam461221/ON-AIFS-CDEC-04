package com.cloud.clinic.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.clinic.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
}
