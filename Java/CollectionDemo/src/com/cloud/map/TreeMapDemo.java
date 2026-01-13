package com.cloud.map;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapDemo {
	
	public static void main(String[] args) {
		
		//TreeMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
		//Comparator.reverseOrder - for sorting in reverse order
		
		
		TreeMap<Integer, String> map = new TreeMap<>();
		map.put(104, "Shivam");
		map.put(101, "Nilima");
		map.put(102, "Kalpana");
		map.put(103, "Amrapali");
		
		System.out.println(map);
		
		TreeMap<String, Integer> fruits = new TreeMap<>(Comparator.reverseOrder());
		
		fruits.put("Mango", 100);
		
		fruits.put("Banana", 40);
		
		fruits.put("Apple", 150);
		
		fruits.put("Orange", 50);
		
		System.out.println(fruits);
		
		
	}
}
