package com.cloud.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.crud.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
