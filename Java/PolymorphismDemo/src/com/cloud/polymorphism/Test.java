package com.cloud.polymorphism;

public class Test {
	
	
	public static void main(String[] args) {
		
		//Upcasting
		Animal animal1 = new Dog();
		
		Animal animal2 = new Cat();
		
		animal1.sound();
		
		animal2.sound();
		
		animal1.display();
		
	}
}
