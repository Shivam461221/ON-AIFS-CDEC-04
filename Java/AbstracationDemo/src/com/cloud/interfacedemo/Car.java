package com.cloud.interfacedemo;

public class Car implements Vehicle, Safety {

	@Override
	public void start() {
		System.out.println("Button start");
	};

	@Override
	public void stop() {
		System.out.println("Button stop");
	}

	@Override
	public void seatbelt() {
		System.out.println("Seat belt enabled");

	}

	@Override
	public void airbag() {
		System.out.println("Air bag enabled");
	};

}
