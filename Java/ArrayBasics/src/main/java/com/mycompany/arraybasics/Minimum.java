/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

public class Minimum {
    //program to get lowest value from an array
    
    public static void getMinimum(int arr[]){
        int min = arr[0];
        
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]<min){
                min = arr[i];
            }
        }
        
        System.out.println("Minimum: "+ min);
    }
    
    
    public static void main(String[] args) {
        int arr[] = {16,12,64,3,76,77,4,6,11};
        
        getMinimum(arr);
    }
}
