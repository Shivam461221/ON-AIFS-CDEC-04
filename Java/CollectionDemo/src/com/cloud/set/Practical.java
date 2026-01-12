package com.cloud.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class Practical {
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>(Arrays.asList("Shivam", "Nilima", "Kalpana", "Ram", "Amrapali", "Ram", "Shivam"));
		
		System.out.println(list);
		
		HashSet<String > uniqueNames = new HashSet<>(list);
		
		System.out.println(uniqueNames);
		
		TreeSet<String> sortedNames = new TreeSet<>(list);
		
		System.out.println(sortedNames);
	}
}
