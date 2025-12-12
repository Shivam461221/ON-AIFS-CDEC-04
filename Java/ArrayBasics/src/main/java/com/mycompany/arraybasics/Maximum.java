/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

public class Maximum {
    //program to get highest value from an array
    
    public static void getMaximum(int numbers[]){
         int max = numbers[0]; //45
        
        for(int i=1; i<numbers.length;i++){
            if(numbers[i]>max){
                max = numbers[i];
            }
        }
        
        System.out.println("Maximum : "+max);
    }
    
    public static void main(String[] args) {
        int arr[] = {45,46,21,78,32,14,111};
        getMaximum(arr);
       
    }
}
