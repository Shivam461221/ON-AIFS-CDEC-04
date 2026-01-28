package com.cloud.rest.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.rest.models.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	List<Student> students = new ArrayList<>(Arrays.asList(new Student(101, "shivam", "MCA", 25), new Student(102, "Nilima", "BCA", 23)));
	
	@GetMapping
	public List<Student> getAllStudents(){
		return students;
	}
	
	@PostMapping
	public String addStudent(@RequestBody Student student) {
		students.add(student);
		
		return "Student added";
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Integer id) {
		return students.stream().filter(s->s.getId()==id).findFirst().orElse(null);
	}
	
	@DeleteMapping("/{id}")
	public String deleteStudentById(@PathVariable Integer id) {
		students.removeIf(s->s.getId()==id);
		
		return "Student Deleted";
	}
	
	@PutMapping("/{id}")
	public String updateStudentById(@PathVariable Integer id, @RequestBody Student student) {
		for(Student s: students) {
			if(s.getId()==id) {
				s.setName(student.getName());
				s.setCourse(student.getCourse());
				
				return "Student updated";
			}
		}
		
		return "Student not updated";
	}
	
}
