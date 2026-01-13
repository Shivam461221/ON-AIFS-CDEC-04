package com.cloud.map;

import java.util.LinkedHashMap;

public class LinkedHashMapDemo {
	
	public static void main(String[] args) {
		
		LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
		
		map.put(101, "Shivam");
		map.put(102, "Nilima");
		map.put(103, "Kalpana");
		map.put(104, "Amrapali");
		
		System.out.println(map);
	}
}
