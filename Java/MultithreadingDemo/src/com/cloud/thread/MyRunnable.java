package com.cloud.thread;

public class MyRunnable implements Runnable{

	@Override
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
