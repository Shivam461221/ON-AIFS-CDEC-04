package com.cloud.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class UserOperation {
	
	public static TreeSet<String> uniqueWords(String inputFile) throws IOException{
		
		String[] str = inputFile.split(" ");
		
		ArrayList<String> list = new ArrayList<>(Arrays.asList(str));
		
		TreeSet<String> unique = new TreeSet<>(list);
		
		return unique;
	}
	
}
