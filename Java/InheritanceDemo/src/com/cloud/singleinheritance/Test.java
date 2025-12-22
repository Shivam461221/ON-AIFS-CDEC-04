package com.cloud.singleinheritance;

public class Test {
	
	public static void main(String[] args) {
		B obj = new B("Shivam");
		
		System.out.println(obj.name);
		System.out.println(obj.message);
		
		obj.show();
		
		//obj.display();
		
	}
}
	