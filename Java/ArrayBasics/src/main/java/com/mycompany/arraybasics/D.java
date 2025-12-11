/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

import java.util.Arrays;

public class D {
    public static void main(String[] args) {
        
        int arr[] = new int[5];
        
        Arrays.fill(arr, 11);
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        
        System.out.println("-------------------");
        
        int newArr[] = Arrays.copyOf(arr, 5);
        
        for (int i = 0; i < newArr.length; i++) {
            System.out.println(newArr[i]);
        }
    }
}
