
package com.mycompany.constructor;

public class Test {
    
    //int, short, byte, long = 0
    //float, double = 0.0
    //boolean = false;
    
    
       
    int age;
    String name;
    
    //parameterized constructor
    public Test(int age, String name){
        this.age = age;
        this.name = name;
    }
    
    
    
    public static void main(String[] args) {
        Test obj1 = new Test(12, "shivam");
        
        System.out.println(obj1.age);
        System.out.println(obj1.name);
        
        
        Test obj2 = new Test(22, "Ajay");
        System.out.println(obj2.name);
        System.out.println(obj2.age);
    }
}
