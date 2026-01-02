package com.cloud.typecasting;

public class Test {
	//
	
	public static void main(String[] args) {
//		byte x;
//		short y;
//		int a;
//		long z;
//		float b;
//		double c;
//		char ch;
//		boolean status;
		
		//type casting - implicit, explicit casting 
		int x = 100;
		
		double y = x; //implicit casting 
		
		System.out.println(x);
		System.out.println(y);
		
		
		double a = 13.5;
		
		int b = (int) a;  //explicit casting
		
		System.out.println(a);
		System.out.println(b);
		
		long mobile = 9754123123l;
		
		int copyNumber =(int) mobile;
		
		System.out.println(mobile);
		System.out.println(copyNumber);
		
//		char ch = 'A';
//		
//		int num = (int) ch;
		
		int num = 66;
	    char ch = (char) num;
		
		System.out.println(ch);
		System.out.println(num);

		
		
	}
}
