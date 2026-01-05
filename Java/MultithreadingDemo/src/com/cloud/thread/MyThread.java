package com.cloud.thread;

public class MyThread extends Thread{

	public void run() {
		
		for(int i = 1; i<=10; i++) {
			try {
				System.out.println(Thread.currentThread().getName()+" count: "+i);
				Thread.sleep(500);
			}
			
			catch(InterruptedException e) {
				System.out.println(e);
			}
		}
	}

}
