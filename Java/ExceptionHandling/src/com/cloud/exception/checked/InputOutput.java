package com.cloud.exception.checked;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputOutput {
	
	public void readFile() throws IOException{
		FileReader reader = new FileReader("C:\\ON-AIFS-CDEC-04\\Java\\hello.txt");
		int ch;
		
		while((ch=reader.read())!=-1) {
			System.out.println((char) ch);
		}
	}

	public static void main(String[] args) {
		
		InputOutput io = new InputOutput();
		
		
		//Exceptional condition
		try {
			io.readFile();
			
		}
		catch(Exception e) {
			System.out.println("File not found or error reading file: "+ e);
		}
	}
}
