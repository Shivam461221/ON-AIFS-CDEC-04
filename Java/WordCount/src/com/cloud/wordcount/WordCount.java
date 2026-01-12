package com.cloud.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class WordCount {
	
	public static void main(String[] args) throws IOException{
		String filePath = "C:\\ON-AIFS-CDEC-04\\Java\\input.txt";
		
		String symbolsFile = "C:\\ON-AIFS-CDEC-04\\Java\\symbols.txt";
		
		String stopwordFIle = "C:\\ON-AIFS-CDEC-04\\Java\\stopwords.txt";
		
		String inputData = FileOperations.getInputFile(filePath);
		System.out.println(inputData);
		
		
		String dataWithoutSymbols = DataOperation.removeSymbols(inputData, symbolsFile);
		System.out.println(dataWithoutSymbols);
		
		String dataWithoutStopwords = DataOperation.removeStopwords(dataWithoutSymbols, stopwordFIle);
		
		System.out.println(dataWithoutStopwords);
		
		TreeSet<String> uniqueWords = UserOperation.uniqueWords(dataWithoutStopwords);
		
		for(String string: uniqueWords) {
			System.out.println(string);
		}
		
		
		
//		ArrayList<Character> symbols = FileOperations.readSymbols(symbolsFile);
//		
//		System.out.println(symbols);
//		
//		ArrayList<String> stopwords = FileOperations.readStopwords(stopwordFIle);
//		
//		System.out.println(stopwords);
//		
//		System.out.println(stopwords.size());
		
	}
}
