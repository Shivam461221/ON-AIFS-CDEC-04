/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.string;

/**
 *
 * @author shiva
 */
public class StringDemo {

    static String name = "Shivam";  //String literal
    
    String name2 = "Shivam";
    
    static String str = new String("Hello java");
    
    static String str2 = new String("Hello java");

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(name);
        System.out.println("Uppercase: " + name.toUpperCase());
        System.out.println("Length: " + name.length());
        System.out.println("Lowercase: " + name.toLowerCase());

        System.out.println("Substring: " + name.substring(3));

        System.out.println("Contains: " + name.contains("hi"));

        System.out.println("Replace: " + name.replace("vam", "vu"));

        String str2 = name + str;

        System.out.println(str2);

        String[] arr = name.split("");

        for (String string : arr) {
            System.out.println(string);
        }

        System.out.println("Char at: " + name.charAt(1));

        String name1 = "Ayush";
        String name2 = "ayush";

        System.out.println(name1.equals(name2));

        System.out.println(name1.equalsIgnoreCase(name2));

        String lastName = " Amrapali ";

        System.out.println(lastName);

        System.out.println(lastName.trim());
        
        
        
        System.out.println(name.concat(" Lowanshi"));

    }
}
