
package com.mycompany.classandobject;

public class Test {
    
    
    public static void main(String[] args) {
        Car car1 = new Car();
        
        car1.start();
        
        Bike.start();
        
        System.out.println(Bike.brand);;
    }
}
