/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.string;

/**
 *
 * @author shiva
 */
public class Buider {
    public static void main(String[] args) {
        StringBuilder para = new StringBuilder("This is my first blog.");
        
        System.out.println(para);
        
        para.append(" This was my first day in Goa");
        
        System.out.println(para);
        
        para.insert(0, "Hello, ");
        
        System.out.println(para);
        
        para.replace(0, 6, "Hi, ");
        
        System.out.println(para);
        
        para.reverse();
        
        System.out.println(para);
    }
}
