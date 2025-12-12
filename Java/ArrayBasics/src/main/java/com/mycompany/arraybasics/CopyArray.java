/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

public class CopyArray {
    
    public static void main(String[] args) {
        int arr[] = {2,5,1,8,3,8,4};
        
        int newArr[] = new int[arr.length];
        
        for(int i=0; i<arr.length;i++){
            newArr[i] = arr[i];
        }
        
        for (int i : newArr) {
            System.out.println(i);
        }
    }
}
