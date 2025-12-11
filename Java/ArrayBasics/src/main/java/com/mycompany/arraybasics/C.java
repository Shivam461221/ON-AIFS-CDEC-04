/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

import java.util.Arrays;

public class C {
    public static void main(String[] args) {
        int arr[] = {11,43,12,54,34,76,54};
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        
        System.out.println("-----------");
        
        int index = Arrays.binarySearch(arr, 54);
        
        System.out.println(index);
        
        //System.out.println(arr.length);
        
       // System.out.println(arr[arr.length-1]);
        
//        Arrays.sort(arr);
//        
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
        
        
    }
}
