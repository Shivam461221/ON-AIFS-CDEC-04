package com.cloud.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.crud.entities.User;
import com.cloud.crud.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<User> addUser(@RequestBody User user){
		User user2 = userService.addUser(user);
		
		if(user2!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(user2);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUser(){
		
		List<User> users = userService.getAllUsers();
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(users);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		return ResponseEntity.ok(userService.deleteUser(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		User user = userService.getUserById(id);
		
		if(user!=null) {
			return ResponseEntity.ok(user);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with Id: "+id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
		User updatedUser = userService.updateUser(id, user);
		
		if(updatedUser!=null) {
			return ResponseEntity.ok(updatedUser);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}
	
	
}
