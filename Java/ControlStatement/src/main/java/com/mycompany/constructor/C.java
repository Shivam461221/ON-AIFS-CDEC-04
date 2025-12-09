/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.constructor;

public class C {
   
    String name;
    boolean isMarried;
    
    public C(){
        
    }
    
    public C(String name, boolean isMarried){
        this.name = name;
        this.isMarried = isMarried;
    }
    
    public C(String name){
        this.name = name;
    }
    
    public C(boolean isMarried){
        this.isMarried = isMarried;
    }
    
    public static void main(String[] args) {
        C obj = new C(false);
        
        System.gc();
        
      //  System.out.println(obj.isMarried);
    }
    
    
}
