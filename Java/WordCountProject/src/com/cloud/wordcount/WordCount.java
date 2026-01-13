package com.cloud.wordcount;

import java.io.IOException;

public class WordCount {
	
	public static void main(String[] args) throws IOException{
		String filePath = "C:\\ON-AIFS-CDEC-04\\Java\\input.txt";
		
		String inputFile = FileOperation.readInputFile(filePath);
		
		System.out.println(inputFile);
	}
}
