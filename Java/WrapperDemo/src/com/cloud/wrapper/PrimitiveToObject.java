package com.cloud.wrapper;

public class PrimitiveToObject {
	
	public static void main(String[] args) {
		int x = 10;
		
		
		//primitive to object
		Integer y = x;  //Auto-boxing
		
		//Object to primitive 
		int a = y;	//unboxing
		
		//
		Integer z = Integer.valueOf(x);
		
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
		
		
		
	}
}
