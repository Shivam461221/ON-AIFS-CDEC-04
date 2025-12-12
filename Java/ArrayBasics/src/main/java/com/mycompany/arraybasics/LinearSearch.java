/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

public class LinearSearch {
    
    public static void linearSearch(int arr[], int element){
        
        int index = 0;
        boolean found = false;
        
        for(int i=0; i<arr.length; i++){
            if(arr[i]==element){
                index = i;
                found = true;
                break;
            }
        }
        
        if(found){
            System.out.println("Element found at index: "+index);
        }
        else{
            System.out.println("Not found");
        }
    }
    
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,13,14};
        
        linearSearch(arr, 7);
        
    }
}
