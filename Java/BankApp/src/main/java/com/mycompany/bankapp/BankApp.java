package com.mycompany.bankapp;

import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {
       

        Scanner sc = new Scanner(System.in);

        BankAccount account = new BankAccount("Shivam", 15000);

        int choice;
        do {
            System.out.println("\n------Welcome to Cloud Bank------");
            System.out.println("Bank Account Holder Name: " + account.getAccountHolderName());
            System.out.println("Press 1 to display balance");
            System.out.println("Press 2 for withdraw money");
            System.out.println("Press 3 for deposit money");
            System.out.println("Press 4 to exit");
            System.out.println("Please Eter your choice to perform Operation");
            
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Current balance is: " + account.getBalance());
                    break;

                case 2:
                    System.out.println("Please insert amount to withraw");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                    
                case 3:
                    System.out.println("Please insert amount to deposit");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                    
                case 4:
                    System.out.println("Thank you for using the Cloud Bank service");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}
