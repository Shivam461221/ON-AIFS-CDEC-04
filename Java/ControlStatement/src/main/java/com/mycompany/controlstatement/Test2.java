
package com.mycompany.controlstatement;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        int num = input.nextInt();
        
        //if-else ladder
        if(num>0){
           // System.out.println("postive number");
           //nested if-else
            if(num%2==0){
                System.out.println("Positive Even number");
            }
            else{
                System.out.println("Positive Odd number");
            }
        }
        else if(num<0){
            if(num%2==0){
                System.out.println("Negative Even number");
            }
            else{
                System.out.println("Negative Odd number");
            }
        }
        else{
            System.out.println("Zero");
        }
        
        
    }
}
