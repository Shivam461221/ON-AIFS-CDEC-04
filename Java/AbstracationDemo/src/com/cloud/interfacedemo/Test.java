package com.cloud.interfacedemo;

public class Test {
	
	public static void main(String[] args) {
		
		Vehicle v1 = new Car();
		
		v1.start();
		
		v1.stop();
		
		v1.fuel();
		
		Vehicle.display();
		
	}
}
