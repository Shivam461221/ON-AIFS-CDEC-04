package com.cloud.list;

import java.util.ArrayList;

public class ArrayListDemo {

	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(12);
		
		list.add(13);
		
		list.add(15);
		
		list.add(17);
		
		System.out.println(list);
		
		list.remove(0);
		
		System.out.println(list);
		
		list.set(0, 10);
		
		System.out.println(list);
		
		System.out.println(list.get(2));
		
		System.out.println(list.size());

	}

}
