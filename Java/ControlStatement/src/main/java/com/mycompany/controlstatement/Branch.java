
package com.mycompany.controlstatement;

public class Branch {
    //Branching statement - break, continue, return
    
    public String getMsg(){
        System.out.println("Hello Java");
        return "hello";
     }
    
    public static void main(String[] args) {
        
        Branch obj = new Branch();
        
        String msg = obj.getMsg();
        //System.out.println(msg);
        
        for (int i = 0; i < 10; i++) {
           if(i==5){
               continue;
           }
            System.out.println(i);
        }
    }
}
