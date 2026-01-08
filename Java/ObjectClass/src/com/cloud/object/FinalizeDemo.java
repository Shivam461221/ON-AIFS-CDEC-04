package com.cloud.object;

public class FinalizeDemo {
	//Garbage Collector
	
	public FinalizeDemo() {
		System.out.println("Object Created");
	}
	
	protected void finalize() {
		System.out.println("Garbage collector called");
	}
	
	
	public static void main(String[] args) {
		
		FinalizeDemo f1 = new FinalizeDemo();
		
		FinalizeDemo f2 = new FinalizeDemo();
		
		f1 = null;
		
		f2= null;
		
		System.gc();
				
	}
}
