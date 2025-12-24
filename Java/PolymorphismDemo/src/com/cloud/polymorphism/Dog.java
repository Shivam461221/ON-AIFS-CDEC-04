package com.cloud.polymorphism;

public class Dog extends Animal {
	
	@Override
	void sound() {
		System.out.println("Dog barks.....");
	}
}
