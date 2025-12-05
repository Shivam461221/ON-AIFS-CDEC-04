
package com.mycompany.methods;

public class StaticMethod {
    
    static public void getMessage(){
        System.out.println("Hello this is a static method");
    }
    
    public static void main(String[] args) {
        StaticMethod.getMessage();
        
        Operation obj = new Operation();
        obj.getSum(20, 30);
        
        
    }
}
