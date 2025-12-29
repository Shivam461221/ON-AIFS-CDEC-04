package com.cloud.inputoutput;

import java.io.File;

public class Demo {
	
	public static void main(String[] args) {
		try {
			File file = new File("C:\\ON-AIFS-CDEC-04\\java\\Hello.txt");
			
			System.out.println(file.exists());
			
			System.out.println(file.getName());
			
			System.out.println(file.getAbsolutePath());
		}
		catch(Exception e) {
			System.out.println("Something went wrong: "+ e);
		}
	}
	
}
