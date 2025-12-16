/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.string;

public class Programs {
    
    //WAP to reverse a string
    public static void reverseString(String str){
        System.out.println(str);
        String rev= "";
        
        for(int i=str.length()-1; i>=0;i--){
            rev+=str.charAt(i);
           // System.out.print(str.charAt(i));
        }
        
        System.out.println(rev);
    }
    
    //Palindrome - Number, String (12321, "madam")
    
    public static void checkPalindrome(String str){
        String rev = "";
        
        for(int i=str.length()-1; i>=0;i--){
            rev+=str.charAt(i);
           // System.out.print(str.charAt(i));
        }
        
        System.out.println((str.equals(rev))? "Palindrome": "Not palindrome");
    }
    
    //STring = This is a java program
    
    public static void wordCount(String str){
        String[] words = str.split(" ");
        System.out.println("Word count is: "+words.length);
    }
    
    public static void removeSpace(String str){
        String result = str.replace(" ", "");
        
        System.out.println(result);
    }
    
    public static void countCharacters(String str){
        String result = str.replace(" ", "");
        String[] characters = result.split("");
        
        System.out.println("Character count is: "+characters.length);
    }
    
    
    
    public static void main(String[] args) {
        //reverseString("Java");
       // checkPalindrome("12323");
       
        //wordCount("This is a Java Programme");
        
       // removeSpace("This is a Java Programme");
       
       countCharacters("This is a Java Programme");
    }
}
