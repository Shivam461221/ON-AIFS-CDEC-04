package com.cloud.thread;

public class Main extends Object{
	public static void main(String[] args) {
		
		//MyRunnable r1 = new MyRunnable();
		//MyRunnable r2 = new MyRunnable();
		
		Thread t1 = new Thread(new MyRunnable());
		Thread t2 = new Thread(new MyRunnable());
		
		
		t1.setName("Thread 1");
		t2.setName("Thread 2");
		
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		
		t1.start();
		t2.start();
	}
}
