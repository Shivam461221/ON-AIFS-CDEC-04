package com.cloud.list;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListDemo {

	public static void main(String[] args) {
		
		ArrayList<Integer> num = new ArrayList<>();
		
		num.add(100);
		num.add(200);
		num.add(300);
		System.out.println(num);
		
		ArrayList<Integer> list = new ArrayList<>(num);
		
		//add single element
		list.add(10);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(10);
		
		//add multiple elements in one go
		list.addAll(0,Arrays.asList(15,16,17,18));
		
		// add another list
		//list.addAll(num);
		
		System.out.println(list);
		
		//get element with index
		System.out.println(list.get(2));
		
		//add element ad specific index
		//list.addAll(0, list)
		
		//remove single elmenet using index
		list.remove(5);
		
		//remove single element by passing element itself
		list.remove(Integer.valueOf(15));
		
		System.out.println(list);
		
		//remove multiple elements
		list.removeAll(Arrays.asList(16,17,18));
		
		System.out.println(list);
		
		//update element using index
		list.set(0, 500);
		
		System.out.println(list);
		
		list.add(0, 600);
				
		System.out.println(list);
		
		//delete all elements in a list
		list.clear();
		
	}

}
