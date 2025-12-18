
package com.mycompany.bankapp;

public class BankAccount {
    private String accountHolderName;
    private double balance;
    
    public BankAccount(String accountHolderName, double balance){
        this.accountHolderName = accountHolderName;
        if(balance>=0){
            this.balance = balance;
        }
        else{
            this.balance = 0;
        }
    }
    
    public double getBalance(){
        return balance;
    }
    
    public String getAccountHolderName(){
        return accountHolderName;
    }
    
    public void deposit(double amount){
        balance+=amount;
        System.out.println("Amount deposited, current balance: "+balance);
    }
    
    public void withdraw(double amount){
        if(amount<=balance){
            balance-= amount;
            System.out.println("Withdrawn, current balance: "+balance);
        }
        else{
            System.out.println("Insufficient Balance");
        }
    }
}
