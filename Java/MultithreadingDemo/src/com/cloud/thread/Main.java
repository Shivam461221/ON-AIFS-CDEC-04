package com.cloud.thread;

public class Main {
	
	public static void main(String[] args) {
		
		
		
		//MyRunnable runnable = new MyRunnable();
		
		Thread t1 = new Thread(new MyRunnable());
		
		t1.setName("Thread 1");
		
		Thread t2 = new Thread(new MyRunnable());
		t2.setName("Thread 2");
		
		Thread t3 = new Thread(new MyRunnable());
		t3.setName("Thread 3");
		
		t1.start();
		
		t2.start();
		
		t3.start();
		
		
		
	}
}
