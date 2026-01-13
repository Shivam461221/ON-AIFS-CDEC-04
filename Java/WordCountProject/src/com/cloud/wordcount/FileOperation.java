package com.cloud.wordcount;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileOperation {
	
	public static String readInputFile(String filePath) throws IOException{
		File file = new File(filePath);
		
		Scanner sc = new Scanner(file);
		String inputData = "";
		while(sc.hasNext()) {
			inputData = inputData.concat(sc.next().concat(" "));
		}
		
		return inputData;
	}
}
