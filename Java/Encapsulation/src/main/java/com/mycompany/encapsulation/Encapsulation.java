

package com.mycompany.encapsulation;

public class Encapsulation {
   private String name = "shivam";
   private String password = "12345";
   private double accountBalance = 120000; 
   
   
   //data - variables
   //code - methods
   public void setName(String name){
       this.name = name;
   }
   
   public String getName(){
       return name;
   }
   
   public void setPassword(String password){
       this.password = password;
   }
   
   public String getPassword(){
       return password;
   }
   
   public void setAccountBalance(double accountBalance){
       this.accountBalance = accountBalance;
   }
   
   public double getAccountBalance(){
       return accountBalance;
   }
   
    public static void main(String[] args) {
        Encapsulation ec = new Encapsulation();
        System.out.println(ec.getName());
        ec.setName("Nilima");
        System.out.println(ec.getName());
    }
    
    
}
