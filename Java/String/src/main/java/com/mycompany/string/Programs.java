/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.string;

public class Programs {
    //java
    public static void reverseString(String str){
        System.out.println(str);
        String rev= "";
        
        for(int i=str.length()-1; i>=0;i--){
            rev+=str.charAt(i);
           // System.out.print(str.charAt(i));
        }
        
        System.out.println(rev);
    }
    
    public static void main(String[] args) {
        reverseString("Java");
    }
}
