package com.cloud.set;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetDemo {
	
	public static void main(String[] args) {
		
		//Hashset not allows duplicate elements 
		//Hashset does not maintain insertion order 
		//HashSet is fastest in all three set classes 
		//It allows one Null value
		
		
		HashSet<Integer> list = new HashSet<>();
		
		list.add(12);
		
		list.add(13);
		
		list.add(2);
		
		list.add(4);
		
		list.add(15);
		
		list.add(8);
		
		list.add(6);
		
		list.add(6);
		
		list.add(15);
		
		System.out.println(list);
		
		list.remove(15);
		
		System.out.println(list);
		
		for(Integer element: list) {
			System.out.println(element);
		}
		Iterator<Integer> it = list.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		list.forEach(element->System.out.println(element));
	}
}
