package com.cloud.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.crud.entities.User;
import com.cloud.crud.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);

	}

	public String deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "User Deleted";
		}

		return "User with specific ID not found";
	}
	
	public User updateUser(Long id, User userData) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(userOptional.isPresent()) {
			User user  = userOptional.get();
			user.setUsername(userData.getUsername());
			user.setPassword(userData.getPassword());
			
			return userRepository.save(user);
		}
		
		return null;
	}

}
