/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.encapsulation.first;

public class A {
    public String name = "Hello Java";
    private int age = 25;
    protected String email = "shivam@123";
    double salary = 15000; //default 
    
    public void show(){
        System.out.println("Method from A class");
    }
    
    public static void main(String[] args) {
        A obj = new A();
        
        System.out.println(obj.name);
        System.out.println(obj.age);
        System.out.println(obj.email);
        System.out.println(obj.salary);
    }
}
