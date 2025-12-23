package com.cloud.vehiclemanagenment;

public class Bike extends Vehicle{
	
	public Bike(String brand, double price, Engine engine) {
		super(brand, price, engine);
	}
	
	@Override
	public double calculateTax() {
		return price*0.05;
	}
	
}
