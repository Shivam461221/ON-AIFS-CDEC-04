/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

public class EvenOdd {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,13,14};

        int even = 0, odd = 0;

        for (int element : arr) {
            if (element % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        
        System.out.println("Even: "+even+" odd: "+odd);
    }

}
