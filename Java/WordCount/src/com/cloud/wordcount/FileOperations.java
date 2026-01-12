package com.cloud.wordcount;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {
	
	public static String getInputFile(String filePath) throws IOException{
		File file = new File(filePath);
		
		Scanner sc = new Scanner(file);
		
		String data = "";
		
		while(sc.hasNext()) {
			data = data.concat(sc.next().concat(" "));
		}
		
		return data;
	}
	
	public static ArrayList<Character> readSymbols(String filePath) throws IOException{
		File file = new File(filePath);
		
		Scanner sc = new Scanner(file);
		
		ArrayList<Character> symbols = new ArrayList<>();
		
		while(sc.hasNext()) {
			symbols.add(sc.next().charAt(0));
		}
		
		return symbols;
	}
	
	public static ArrayList<String> readStopwords(String filePath) throws IOException{
		File file = new File(filePath);
		
		Scanner sc = new Scanner(file);
		
		ArrayList<String> stopwords = new ArrayList<>();
		
		while(sc.hasNext()) {
			stopwords.add(sc.next());
		}
		
		return stopwords;
	}
	
	
}
