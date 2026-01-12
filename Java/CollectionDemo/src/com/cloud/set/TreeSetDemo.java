package com.cloud.set;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {
	
	public static void main(String[] args) {
		
		//TreeSet does not allow duplicate elements
		//TreeSet sorts element in natural order
		//It does not allow null values
		//It is slower than other set classes
		
		TreeSet<Integer> list = new TreeSet<>();
		
		list.add(12);

		list.add(13);

		list.add(2);

		list.add(4);

		list.add(15);

		list.add(8);

		list.add(6);

		list.add(6);

		list.add(15);
		
		//list.add(null);
		
		//list.add(null);
		
		System.out.println(list);
		
	}
}
