package com.cloud.inputoutput;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteFile {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Write something\n");
		String data = sc.nextLine();
		
		try {
			FileWriter writer = new FileWriter("C:\\ON-AIFS-CDEC-04\\Java\\index.html");
			writer.write(data);
			System.out.println("File written");
			
			writer.close();
		} catch (IOException e) {

			System.out.println("Error: "+ e);
		}
		
		sc.close();
	}
}
