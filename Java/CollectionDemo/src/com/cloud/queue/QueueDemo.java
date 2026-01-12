package com.cloud.queue;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDemo {
	
	public static void main(String[] args) {
		
		//Queue<String> list = new PriorityQueue();
		Queue<String> list = new ArrayDeque<>();
		
		list.add("First");
		list.add("Second");
		list.add("Third");
		list.add("Fourth");
		
		System.out.println(list);
		
		System.out.println(list.peek());
		
		//list.poll();
		list.remove();
		
		System.out.println(list);
		
		System.out.println(list.peek());
		
		list.remove();
		
		System.out.println(list);
		
	}
}
