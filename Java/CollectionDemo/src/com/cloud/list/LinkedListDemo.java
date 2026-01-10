package com.cloud.list;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListDemo {
	
	public static void main(String[] args) {
		
		LinkedList<String> fruits = new LinkedList<>();
		
		fruits.add("Mango");
		fruits.add("Banana");
		
		fruits.add("Apple");
		fruits.add("Pineapple");
		
		System.out.println(fruits);
		
		fruits.addLast("Grapes");
		
		fruits.addFirst("Watermelon");
		
		System.out.println(fruits);
		
		fruits.removeFirst();
		System.out.println(fruits);
		
		fruits.addAll(Arrays.asList("Kiwi", "Dragon", "Litchi"));
		
		System.out.println(fruits);
		
	}
}
