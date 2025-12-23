package com.cloud.vehiclemanagenment;

public class Engine {
	protected String type;
	protected int horsePower;

	public Engine(String type, int horsePower) {
		this.type = type;
		this.horsePower = horsePower;
	}

	public void getEngineDetails() {
		System.out.println("Engine Details: ");
		System.out.println("Engine type: " + type);
		System.out.println("Engine power: " + horsePower);
	}
}
