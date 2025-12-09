/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.constructor;

public class A {
    int age;
    
    public A(int age){
        this.age = age;
    }
    
    public static void main(String[] args) {
        A obj = new A(22);
        
        System.out.println(obj.age);
        
        A obj2 = new A(25);
        
        System.out.println(obj2.age);
    }
}
