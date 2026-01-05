package com.cloud.thread;

public class Test {
	
	public static void main(String[] args) {
		
		MyThread t1 = new MyThread();
		
		t1.setName("Thread 1");
		
		MyThread t2 = new MyThread();
		
		t2.setName("Thread 2");
		
		t1.start();
		
		t2.start();
		

		MyThread t3 = new MyThread();
		
		t3.setName("Thread 3");
		
		t3.start();
		
//		SingleThread obj1 = new SingleThread();
//		
//		SingleThread obj2 = new SingleThread();
//		
//		obj1.print("obj1");
//		
//		obj2.print("obj2");
	}
}
