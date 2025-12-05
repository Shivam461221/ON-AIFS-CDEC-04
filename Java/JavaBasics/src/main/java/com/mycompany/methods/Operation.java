
package com.mycompany.methods;

public class Operation {
    
//    public int getSum(int x, int y){
//        return x+y;
//    }
    
    public void getSum(int x, int y){
        System.out.println("The sum is: "+ (x+y));
    }
    
    public void getMultiplication(int x, int y){
        System.out.println("The output is: "+(x*y));
    }
    
    public static void main(String[] args) {
        Operation obj1 = new Operation();
        
//        int sum = obj1.getSum(30, 20);
//        System.out.println("The sum is: "+sum);

       obj1.getSum(10, 40);
       obj1.getMultiplication(10, 10);
       
       obj1.getMultiplication(10, 20);
       
       StaticMethod.getMessage();
    }
    
}
