package com.cloud.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.jpa.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	

}
