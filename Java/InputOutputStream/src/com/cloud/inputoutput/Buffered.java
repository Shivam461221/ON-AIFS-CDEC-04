package com.cloud.inputoutput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Buffered {

	public static void main(String[] args) {

//		try {
//			//FileReader reader = new FileReader("");
//			//BufferedReader bufferedReader = new BufferedReader(reader);
//			BufferedReader reader = new BufferedReader(new FileReader("C:\\ON-AIFS-CDEC-04\\java\\Hello.txt"));
//			
//			String data;
//			while((data=reader.readLine())!=null) {
//				System.out.print(data+"\n");
//			}
//		}
//		
//		catch(Exception e) {
//			System.out.println(e);
//		}

//		try {
//			//FileReader reader = new FileReader("");
//			//BufferedReader bufferedReader = new BufferedReader(reader);
//			BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\ON-AIFS-CDEC-04\\java\\HelloFile.txt"));
//			writer.write("New file using buffered writer\n");
//			writer.write("another line");
//			System.out.println("File written");
//			
//			writer.close();
//			
//		}
//		
//		catch(Exception e) {
//			System.out.println(e);
//		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\ON-AIFS-CDEC-04\\java\\HelloFile.txt"));) 
		{
			// FileReader reader = new FileReader("");
			// BufferedReader bufferedReader = new BufferedReader(reader);

			writer.write("New file using buffered writer\n");
			writer.write("another line");
			System.out.println("File written");

		}

		catch (Exception e) {
			System.out.println(e);
		}
	}
}
