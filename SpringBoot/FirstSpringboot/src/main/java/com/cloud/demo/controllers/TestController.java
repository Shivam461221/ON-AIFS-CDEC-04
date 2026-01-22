package com.cloud.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
		
	@GetMapping("/")
	public String getHello() {
		return "Test Method";
	}
	
	@PostMapping()
	public String postDemo() {
		return "post method test controller";
	}
}
