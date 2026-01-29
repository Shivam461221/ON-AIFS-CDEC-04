package com.cloud.jpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.jpa.entities.Student;
import com.cloud.jpa.repositories.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@PostMapping("/")
	public Student addStudent(@RequestBody Student student) {
			return studentRepository.save(student);
			//System.out.println(studentRepository.save(student));
			
	}
	

}
