package com.cloud.abstraction;

public class Bike extends Vehicle{
	
	public Bike(String name) {
		super(name);
	}
	
	@Override
	void display() {
		System.out.println("Bike with 155 CC engine");
	}
	
	@Override
	void start() {
		System.out.println("Bike starts with key ignition");
	}
	
	@Override
	void seatbelt() {
		System.out.println("Seatbelt implemented");
		
	}
}
