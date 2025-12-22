package com.cloud.singleinheritance;

public class B extends A{
	
	String message = super.name;
	
	
	
	public B(String name) {
		super(name);
	}

	void display() {
		System.out.println("Child class method");
	}
	
	@Override
	void show() {
		super.show();
		System.out.println("Hello ");
	}
}
