package com.cloud.vehiclemanagenment;

import java.util.Scanner;

public class VehicleManagement {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Vehicle vehicle = null;

		while (true) {
			System.out.println("\n-------Vehicle Management System-----");
			System.out.println("1. Create Car");
			System.out.println("2. Create Bike");
			System.out.println("3. create Truck");
			System.out.println("4. Display Vehicle Details");
			System.out.println("5. Calculate Tax");
			System.out.println("6. Exit ");
			System.out.println("Enter Your Choice");

			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				vehicle = new Car("Toyota", 1200000, new Engine("Diesel", 120));
				System.out.println("Car created");
				break;
			case 2:
				vehicle = new Bike("Bajaj", 200000, new Engine("Petrol", 20));
				System.out.println("Bike created");
				break;
			case 3:
				vehicle = new Truck("Tata", 4200000, new Engine("Diesel", 300));
				System.out.println("Truck created");
				break;
			case 4:
				if (vehicle != null) {
					vehicle.getVehicleDetails();
				} else {
					System.out.println("No vehicle created");
				}
				break;
			case 5:
				if (vehicle != null) {
					System.out.println("Tax: " + vehicle.calculateTax());
				} else {
					System.out.println("No vehicle created");
				}
				break;
			case 6:
				System.out.println("Existing....");
				sc.close();
				return;

			default:
				System.out.println("Invalid choice");
			}

		}
	}
}
