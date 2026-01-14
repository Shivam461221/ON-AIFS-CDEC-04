package com.cloud.wordcount;

import java.io.IOException;
import java.util.ArrayList;

public class WordCount {
	
	public static void main(String[] args) throws IOException{
		String filePath = "C:\\ON-AIFS-CDEC-04\\Java\\input.txt";
		
		String symbolsPath = "C:\\ON-AIFS-CDEC-04\\Java\\symbols.txt";
		
		String stopwordsPath = "C:\\ON-AIFS-CDEC-04\\Java\\stopwords.txt";
		
		
		//Read Raw data file
		System.out.println("------------------Raw Data--------------");
		System.out.println();
		String inputFile = FileOperation.readInputFile(filePath);
		System.out.println(inputFile);
		
		System.out.println();
		System.out.println("------------------Data without symbols--------------");
		System.out.println();
		//Remove symbols from raw data
		String dataWithoutSymbols = DataOperation.removeSymbols(inputFile, symbolsPath);
		System.out.println(dataWithoutSymbols);
		
		
		System.out.println();
		System.out.println("------------------Data without stopwords and symbols--------------");
		System.out.println();
		//Remove stopwords from dataWithoutSymbols
		String dataWithoutStopwords = DataOperation.removeStopwords(dataWithoutSymbols, stopwordsPath);
		System.out.println(dataWithoutStopwords);
		
		
//		ArrayList<Character> symbols = FileOperation.readSymbols(symbolsPath);
//		
//		symbols.forEach(s->System.out.println(s));
//		
//		ArrayList<String> stopwords = FileOperation.readStopwords(stopwordsPath);
//		
//		stopwords.forEach(s->System.out.println(s));
	}
}
