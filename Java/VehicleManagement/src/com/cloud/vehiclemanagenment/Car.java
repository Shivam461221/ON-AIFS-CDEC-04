package com.cloud.vehiclemanagenment;

public class Car extends Vehicle{
	
	
	
	public Car(String brand, double price, Engine engine) {
		super(brand, price, engine);
	}
	
	@Override
	public double calculateTax() {
		return price*0.10;
	}

}
