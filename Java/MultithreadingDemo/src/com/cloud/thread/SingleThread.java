package com.cloud.thread;

public class SingleThread {
	
	public void print(String name) {
		for(int i = 1; i<=10; i++) {
			System.out.println(name+" Count: "+i);
		}
	}
}
