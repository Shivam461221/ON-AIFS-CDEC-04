/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cloudconstruction;

public class Plot {
    int length;
    int breadth;
    String sector;
    
    public Plot(){
        this.length = 50;
        this.breadth = 50;
        this.sector = "A";
    }
    
    public Plot(int breadth){
        this.length = 50;
        this.breadth = breadth;
        this.sector = "B";
    }
    
    public Plot(int length, int breadth){
        this.length = length;
        this.breadth = breadth;
        this.sector = "C";
    }
    
    public int getPlotArea(){
        return length*breadth;
    }
    
}
