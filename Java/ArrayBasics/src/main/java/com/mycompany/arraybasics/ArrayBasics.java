
package com.mycompany.arraybasics;

public class ArrayBasics {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        double[] arr = new double[5];
        
        arr[0] = 10;
        arr[1] = 15;
        arr[2] = 20;
       
        
        //System.out.println(arr[0]);
        
        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
        
        System.out.println("-----------------------");
        
        int arr2[] = {1,2,3,4,5,6,7,8};
        
       // System.out.println(arr2[0]);
       
       arr2[0] = 12;
       
       for(int i = 0; i<arr2.length;i++){
           System.out.println(arr2[i]);
       }
        
        
    }
}
