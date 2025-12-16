/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.string;

public class Pattern {

    public static void printRightAngleTraingle(int rows) {

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }

    public static void printSquare(int rows) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }

    public static void printPyramid(int rows) {
        for (int i = 0; i < rows; i++) {

            for (int j = rows; j > i; j--) {
                System.out.print(" ");
            }

            for (int k = 0; k <= i; k++) {
                System.out.print("* ");
                //System.out.print(k+1+" ");
            }

            System.out.println();
        }
    }

    public static void printReversePyramid(int rows) {
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j <=i; j++) {
                System.out.print(" ");
            }

            for (int k = rows; k > i; k--) {
                
                System.out.print("* ");
                //System.out.print(k+1+" ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("------Right Angle Traingle-------");
        printRightAngleTraingle(5);
        System.out.println("------Sqaure-------");
        printSquare(5);
        System.out.println("-------Pyramid------");
        printPyramid(5);
       // System.out.println("-------Reverse Pyramid------");
        printReversePyramid(5);
    }
}
