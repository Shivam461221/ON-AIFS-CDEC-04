/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arraybasics;

public class B {
    
    
    
    //  [11, 12]
    //  [13, 15]
    
    public static void main(String[] args) {
        int matrix[][] = new int[3][3];
        
        matrix[0][0] = 11;
        matrix[0][1] = 12;
        matrix[0][2] = 10;
        matrix[1][0] = 13;
        matrix[1][1] = 15;
        matrix[1][2] = 22;
        matrix[2][0] = 17;
        matrix[2][1] = 19;
        matrix[2][2] = 34;
        
        for (int i = 0; i < matrix.length; i++) {
            for(int j =0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            
            System.out.println();
        }
        
    }
}
