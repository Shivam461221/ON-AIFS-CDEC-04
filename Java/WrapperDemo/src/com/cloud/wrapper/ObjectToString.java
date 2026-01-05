package com.cloud.wrapper;

public class ObjectToString {
	public static void main(String[] args) {
		
		Double num = 122.1;
		
		System.out.println(num);
		
		String str = num.toString();
		
		System.out.println(str.concat("hello"));
		
	}
}
