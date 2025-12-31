package com.cloud.exception.unchecked;

public class NullPointer {
	
	public static void main(String[] args) {
		
		String name = "Shivam";
		
		//if(name!=null)
		//System.out.println(name.length());
		
		try {
			System.out.println(name.length());
		}
		catch(NullPointerException e) {
			System.out.println("Your object is null you cannot call methods");
		}
		finally {
			System.out.println("Finaly");
		}
		
		
		System.out.println("Bottom");
	}
}
