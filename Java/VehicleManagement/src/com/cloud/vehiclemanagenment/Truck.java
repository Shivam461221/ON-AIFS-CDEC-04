package com.cloud.vehiclemanagenment;

public class Truck extends Vehicle{
	
	public Truck(String brand, double price, Engine engine) {
		super(brand, price, engine);
	}
	
	@Override
	public double calculateTax() {
		return price*0.15;
	}
}
