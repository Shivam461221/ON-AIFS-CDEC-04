package com.cloud.abstraction;

public class Test{
	
	
	
	public static void main(String[] args) {
		
		Vehicle v1 = new Bike("Yamaha");
		
		v1.display();
		
		v1.show();
		
		v1.start();
		
		Vehicle v2 = new Car("Tata");
		
		v2.show();
		
		v2.start();
		
		v2.display();
		
		
	}
}
