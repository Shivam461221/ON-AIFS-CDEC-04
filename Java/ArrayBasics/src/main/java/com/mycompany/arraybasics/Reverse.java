/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

public class Reverse {
    
    
    public static void main(String[] args) {
        int[] arr= {1,2,3,4,5};
        
        System.out.println("Origin array");
        
        for(int element : arr)System.out.println(element);
        
        System.out.println("Reverse array");
        
        for(int i=arr.length-1;i>=0;i--){
            System.out.println(arr[i]);
        }
        
    }
}
