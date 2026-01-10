package com.cloud.list;

import java.util.ArrayList;
import java.util.Collections;

public class SortAndSearch {

	public static void main(String[] args) {
		ArrayList<Integer> num = new ArrayList<>();

		num.add(100);
		num.add(200);
		num.add(300);
		num.add(30);
		num.add(50);
		num.add(20);
		num.add(80);
		num.add(500);
		num.add(450);
		num.add(540);

		System.out.println(num);
		
		//sorting in ascending order
		Collections.sort(num);
		
		//reverse list
		//Collections.reverse(num);
		
		//sorting in descending order
		//Collections.sort(num, Collections.reverseOrder());
		
		System.out.println(num);
		
		int index = Collections.binarySearch(num, 500);
		
		System.out.println("Element found at Index: "+index);
	}
}
