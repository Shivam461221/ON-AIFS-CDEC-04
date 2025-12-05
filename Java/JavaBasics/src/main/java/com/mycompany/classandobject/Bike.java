
package com.mycompany.classandobject;

public class Bike {
    
    //static - variable, methods, fields
    
    //static variable / class variable
    static String brand = "Yamaha";
    
    static void start(){
            System.out.println("Bike starts");
    }
    
    static{
        System.out.println("Static field");
    }
    
    public static void main(String[] args) {
        System.out.println("Main method called");
        
        System.out.println("Bike brand: "+Bike.brand);
        
        start();
    }
    
}
