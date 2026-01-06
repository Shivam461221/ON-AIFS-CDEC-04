package com.cloud.thread;

public class MyThread extends Thread{
	
	public void run() {
		for(int i = 1; i<=10; i++) {
			System.out.println(Thread.currentThread().getName()+" Count: "+i);
			
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
		}
	}
	
}
