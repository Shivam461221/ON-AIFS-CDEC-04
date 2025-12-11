/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

import java.util.Scanner;

public class A {
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int arr[] = new int[5];
        
        System.out.println("Enter 5 number");
        
        for(int i=0; i<arr.length; i++){
            arr[i] = sc.nextInt();
        }
        
        System.out.println("Output");
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
