package com.cloud.inputoutput;

import java.io.FileReader;

public class ReadFile {
	
	public static void main(String[] args) {
		
		try {
			FileReader reader = new FileReader("C:\\ON-AIFS-CDEC-04\\java\\Hello.txt");
			int ch;
			
			while((ch= reader.read())!=-1	) {
				System.out.print((char) ch);
			}
			
			reader.close();
		}
		catch(Exception e) {
			System.out.println("Something went wrong: "+ e);
		}
		
		
		
	}
}
