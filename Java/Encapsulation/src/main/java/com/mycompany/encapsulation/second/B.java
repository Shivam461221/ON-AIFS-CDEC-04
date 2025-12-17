/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.encapsulation.second;
import com.mycompany.encapsulation.first.A;
/**
 *
 * @author shiva
 */
public class B extends A {
     public static void main(String[] args) {
         A obj = new A();
         System.out.println(obj.name);
         
         obj.show();
          B obj1 = new B();
         System.out.println(obj1.email);
         //System.out.println(obj1.salary);
    }
      
}
