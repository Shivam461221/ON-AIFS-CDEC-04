
package com.mycompany.methods;

public class Test {
    
   // private String name = "Shivam";
    
    //parameter - parameters are the values you use to perform operations in a method
    
    //method with paramter without return type
    public void printName(String name){
        System.out.println("name: "+name);
    }
    
    //method without parameter without return type
    public void print(){
        System.out.println("Hello ");
    }
    
    //method with parameter with return type
    public String getName(String name){
        return name;
    }
    
    //method with return type without paramters
    public String getMessage(){
        return "Hello Java";
    }
    
    public static void main(String[] args) {
        Test t1 = new Test();
        
        //arguments - the actual value you pass when you call the methods
        t1.printName("Shivam");
        t1.printName("Kalpana");
        t1.printName("Amrapali");
        
        String name = t1.getName("Om");
        System.out.println("Return type"+ name);
    }
            
    
}
