package com.cloud.multilevel;

public class Test {
	
	public static void main(String[] args) {
		C obj = new C();
		
		System.out.println(obj.classAvariable);
		System.out.println(obj.classBvariable);
		System.out.println(obj.classCvariable);
		
		obj.classAMethod();
		obj.classBMethod();
		obj.classCMethod();
		
	}
}
