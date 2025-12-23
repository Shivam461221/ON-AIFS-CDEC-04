package com.cloud.vehiclemanagenment;

public class Vehicle {
	protected String brand;
	protected double price;
	protected Engine engine;
	
	public Vehicle(String brand, double price, Engine engine) {
		this.brand = brand;
		this.price = price;
		this.engine = engine;
	}
	
	public void getVehicleDetails() {
		System.out.println("Vehicle details: ");
		System.out.println("Brand: "+brand);
		System.out.println("Price: "+price);
		engine.getEngineDetails();
	}
	
	public double calculateTax() {
		return price*0.15;
	}
}
