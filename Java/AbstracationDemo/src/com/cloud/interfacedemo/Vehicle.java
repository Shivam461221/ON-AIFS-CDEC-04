package com.cloud.interfacedemo;

public interface Vehicle {
	
	//Varibles = final and static 
	String name = "Vehicle blueprint 2025";
	
	void start();
	
	void stop();
	
	static void display() {
		System.out.println("Static method");
	}
	
	default void fuel() {
		System.out.println("default method");
	}
	
}
