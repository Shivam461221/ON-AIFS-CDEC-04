package com.cloud.exception.unchecked;

public class Arithmetic {
	public static void main(String[] args) {
		
		System.out.println("Starting");
		 int a = 10;
		 
		 int b = 10;
		 
		 try {
			 int c = a/b;
			 
			 System.out.println("Result: "+c); 
		 }
		 catch(ArithmeticException e) {
			 System.out.println("You can not divide by zero, change the number");
		 }
		
		 
		 System.out.println("End");
	}
}
