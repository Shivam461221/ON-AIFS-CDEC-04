package com.mycompany.controlstatement;

public class Looping {

    //while, do-while, for, foreach
    //when we need to execute a block of code multiple times then we can use loops
    public static void main(String[] args) {

        //int i = 1;
//        while(i<=10){
//            System.out.println("Hello");
//            i++;
//        }
//        
//        do{
//            System.out.println("hello");
//            i++;
//        }while(false);
//        
//        
//        for(int j=9;j>=0;j--){
//            System.out.println(j);
//        }
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }
    }

    }
