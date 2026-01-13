package com.cloud.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
	public static void main(String[] args) {
		
		HashMap<Integer, String> map = new HashMap<>();
		
		map.put(101, "Shivam");
		map.put(102, "Nilima");
		map.put(103, "Kalpana");
		map.put(104, "Amrapali");
		
		System.out.println(map);
		
		System.out.println(map.get(103));
		
		map.remove(101);
		
		System.out.println(map);
		
		System.out.println(map.containsKey(102));
		
		System.out.println(map.containsValue("Kalpana"));
		
		System.out.println(map.keySet());
		
		System.out.println(map.values());
		
		System.out.println(map.isEmpty());
		
		System.out.println(map.size());
		
		System.out.println(map.entrySet());
		
		//map.clear()  //to delete all enteries
		
		System.out.println("--------------------------------------------------");
		
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			Integer key = entry.getKey();
			String val = entry.getValue();
			System.out.println(key+" : "+val);
		}
		
		System.out.println("--------------------------------------------------");
		
		map.forEach((key, value)->System.out.println(key+" : "+value));
		
		System.out.println("--------------------------------------------------");
		
		for(Integer key: map.keySet()) {
			System.out.println(key+" : "+ map.get(key));
		}
	}
}
