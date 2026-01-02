package com.cloud.wrapper;

public class ObjectToPrimitive {
	
	public static void main(String[] args) {
		Integer a = 10;
		
		
		int b = a.intValue();
		
		System.out.println(a);
		System.out.println(b);
		
		Double c = 100.5;
		
		double d = c.doubleValue();
		
		System.out.println(c);
		System.out.println(d);
		
		
	}
	
}
