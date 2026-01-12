package com.cloud.set;

import java.util.LinkedHashSet;

public class LinkedHashSetDemo {

	public static void main(String[] args) {
		
		//LinkedHashSet not allows duplicate elements 
		//LinkedHashSet maintains insertion order 
		//LinkedHashSet is slower than HashSet
		//It allows one Null value

		LinkedHashSet<Integer> list = new LinkedHashSet<>();

		list.add(12);

		list.add(13);

		list.add(2);

		list.add(4);

		list.add(15);

		list.add(8);

		list.add(6);

		list.add(6);

		list.add(15);
		
		list.add(null);
		
		list.add(null);
		
		System.out.println(list);
	}
}
