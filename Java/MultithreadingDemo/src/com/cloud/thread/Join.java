package com.cloud.thread;

public class Join {
	public static void main(String[] args) {
		
		
		Thread t1 = new Thread(()->{
			for(int i=0; i<10;i++) {
				System.out.println(Thread.currentThread().getName()+" count: "+i);
			}
		});
		
		Thread t2 = new Thread(()->{
			for(int i=0; i<10;i++) {
				System.out.println(Thread.currentThread().getName()+" count: "+i);
			}
		});
		
		
		t1.setName("Thread 1");
		t2.setName("Thread 2");
		
		
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t1.start();
		t2.start();
		
	}
}
