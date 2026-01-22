package com.cloud.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
	
	@GetMapping("/get")
	public String getHello() {
		return "Demo method";
	}
	
	
}
