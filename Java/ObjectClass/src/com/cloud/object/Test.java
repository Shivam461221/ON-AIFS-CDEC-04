package com.cloud.object;

//import java.lang.*;

public class Test {
	String name;
	int age;
	
	public Test(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "name: "+name+", age: "+age;
	}
	
	@Override
	public boolean equals(Object obj) {
		Test t = (Test) obj;
		return this.name.equals(t.name) && this.age==t.age;
		
	}
	

	public static void main(String[] args) {
		Test t1 = new Test("Shivam", 25);
		
		Test t2 = new Test("shivam", 25);
		
		System.out.println(t1);
		
		System.out.println(t2);
		
		//String s1 = "shivam";
		
		//String s2 = "Shivam";
		
		//System.out.println(s1.equals(s2));
		
		System.out.println(t1.equals(t2));
		
		System.out.println(t1.hashCode());
		
		System.out.println(t2.getClass());
		
		//t1 = null;
		
	}

}
