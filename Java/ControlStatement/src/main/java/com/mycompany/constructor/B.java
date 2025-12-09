/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.constructor;

public class B {
    
    int age;
    String name;
    
    //No-args contructor
    public B(){
        this.age = 25;
        this.name = "Om";
    }
    
    public static void main(String[] args) {
        B obj = new B();
        
        System.out.println(obj.age);
        System.out.println(obj.name);
    }
    
    
}
