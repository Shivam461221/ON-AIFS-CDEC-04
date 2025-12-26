package com.cloud.abstraction;

public class Car extends Vehicle{
	
	public Car(String name) {
		super(name);
	}
	
	@Override
	void display() {
		System.out.println("Car with 1000 CC engine");
	}
	
	@Override
	void start() {
		System.out.println("Car starts with Start button");
	}

	@Override
	void seatbelt() {
		System.out.println("Seatbelt implemented");
		
	}
	
	
}
