package com.cloud.polymorphism;

public class Operation {
	//Polymorphism - Many forms
	
	//Compile time (Static binding)- Method overloading
	//Run time (Dynamic binding)- Method overriding
	
	int sum(int a, int b) {
		System.out.println("method 1");
		return a+b;
	}
	
	double sum(double a, double b){
		System.out.println("method 2");
		return a+b;
	}
	
	int sum(int a, int b, int c) {
		System.out.println("method 3");
		return a+b+c;
	}
	
	void display(String name, int age) {
		System.out.println("name: "+name);
		System.out.println("age: "+age);
	}
	
	void display(int age, String name) {
		System.out.println("age: "+age);
		System.out.println("name: "+name);
	}
	
	public static void main(String[] args) {
		Operation ops = new Operation();
		
		System.out.println(ops.sum(10.0, 20.0));
		System.out.println(ops.sum(10, 20));
		System.out.println(ops.sum(10, 20, 30));
		
	   ops.display( "Shivam", 25);
	}
}
