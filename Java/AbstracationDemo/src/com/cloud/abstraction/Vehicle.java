package com.cloud.abstraction;

 abstract class Vehicle extends Safety {
	String name;
	
	public Vehicle(String name) {
		this.name = name;
	}
	
	public void show() {
		System.out.println("Concrete method :"+ name);
	}
	
	abstract void display();
	
	abstract void start();
}
