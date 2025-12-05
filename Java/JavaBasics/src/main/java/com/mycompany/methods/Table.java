package com.mycompany.methods;

public class Table {

    public static void printTable(int num) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(num+"*"+i+" = "+num * i);
        }
    }
    
    
    public static void main(String[] args) {
        //Table ob1 = new Table();
        
        printTable(23);
        
        printTable(44);
    }
}
