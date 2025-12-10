
package com.mycompany.cloudconstruction;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Operation{
    
    public static double getPlotPrice(Plot plot){
        int area = plot.getPlotArea();
        
        if(plot.sector.equalsIgnoreCase("A")){
            return area*Utils.SECTOR_A_PRICE+Utils.DEVELOPMENT_CHARGES;
        }
        else if(plot.sector.equalsIgnoreCase("B")){
            return area*Utils.SECTOR_B_PRICE+Utils.DEVELOPMENT_CHARGES;
        }
        else if(plot.sector.equalsIgnoreCase("C")){
            return area*Utils.SECTOR_C_PRICE+Utils.DEVELOPMENT_CHARGES;
        }
        
        return 0;
    }
    
//    public static String getPlotPriceFormatted(Plot plot) {
//        String price = getPlotPrice(plot); // Get the raw price
//    if (price.equals("0")) return "0";
//    
//    // Format without scientific notation
//        DecimalFormat df = new DecimalFormat("#");
//      df.setRoundingMode(RoundingMode.CEILING.DOWN); // Adjust as needed
//    return df.format(price);
//}
//
//// Or return a formatted string directly:
//public static String getPlotPrice(Plot plot) {
//    int area = plot.getPlotArea();
//    double price = 0;
//    
//    if (plot.sector.equalsIgnoreCase("A")) {
//        price = area * Utils.SECTOR_A_PRICE + Utils.DEVELOPMENT_CHARGES;
//    }
//    else if (plot.sector.equalsIgnoreCase("B")) {
//        price = area * Utils.SECTOR_B_PRICE + Utils.DEVELOPMENT_CHARGES;
//    }
//    else if (plot.sector.equalsIgnoreCase("C")) {
//        price = area * Utils.SECTOR_C_PRICE + Utils.DEVELOPMENT_CHARGES;
//    }
//    
//    if (price == 0) return "0";
//    
//    // Format to full figure without scientific notation
//    return String.format("%.0f", price);
//}
    
    public static void getInvoice(Plot plot){
        System.out.println("-----Cloud Contructions Pvt. Ltd.------");
        System.out.println("Plot sector: "+ plot.sector);
        System.out.println("Plot length: "+ plot.length);
        System.out.println("Plot breadth: "+ plot.breadth);
        System.out.println("Plot Area: "+ plot.getPlotArea());
        System.out.println("Plot Price: "+ Operation.getPlotPrice(plot));
    }
}
