/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.encapsulation;

public class Test {
    
    public static void main(String[] args) {
        Bank account = new Bank();
        //account.balance= 15000;
        System.out.println("Balance: "+account.getBalance());
        account.withdraw(10000);
         System.out.println("Balance: "+account.getBalance());
         account.deposit(20000);
          System.out.println("Balance: "+account.getBalance());
    }
}
