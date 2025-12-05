package com.mycompany.classandobject;

public class Car {

    //non-static- variables, methods, field
    //instance varible
    String brand = "TATA";
    String model = "Harrier";

    public void start() {
        System.out.println("Engine Starts");
    }

    public void stop() {
        System.out.println("Engine Stops");
    }

    {
        System.out.println("non-static field");
    }

    public static void main(String[] args) {
        System.out.println("Main method called.....");
        Car car1 = new Car();

        Car car2 = new Car();
        car1.start();
        car1.stop();

        System.out.println("Brand: " + car1.brand);
    }
}
