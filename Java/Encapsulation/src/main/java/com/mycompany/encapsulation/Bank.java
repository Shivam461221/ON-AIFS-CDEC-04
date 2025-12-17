/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.encapsulation;

/**
 *
 * @author shiva
 */
public class Bank {

    private double balance = 12000;

    public void withdraw(double amount) {
        if (amount < balance) {
            balance -= amount;
            System.out.println("Withdraw successfull, amount: "+amount);
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited amount: "+amount);
    }

    public double getBalance() {
        return balance;
    } 
}
