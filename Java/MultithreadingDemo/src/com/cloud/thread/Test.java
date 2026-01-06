package com.cloud.thread;

public class Test {

	public static void main(String[] args) {
		
		MyThread t1 = new MyThread();
		
		MyThread t2 = new MyThread();
		
		t1.setName("Thread 1");
		
		t2.setName("Thread 2");
		
		System.out.println(t1.isAlive());
		
		t1.start();
		
		System.out.println(t1.isAlive());
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.start();
		
		System.out.println("Main thread");
		
		System.out.println(t1.isAlive());
	}

}
