package com.cloud.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

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
		
		System.out.println();
		System.out.println("------------------Unique words List--------------");
		System.out.println();
		
		TreeSet<String> unique = UserOperation.uniqueWordList(dataWithoutStopwords);
		
		unique.forEach(element->System.out.println(element));
		
		System.out.println();
		System.out.println("------------------Unique Words List with Frequency--------------");
		System.out.println();
		
		Map<String, Integer> map = UserOperation.wordCount(dataWithoutStopwords);
		System.out.println("--Word : Frequency--");
		
		map.forEach((k,v)->System.out.println(k+" : "+v));
		
		System.out.println();
		System.out.println("------------------Characters Frequency--------------");
		System.out.println();
		
		Map<Character, Integer> charCount = UserOperation.characterCount(dataWithoutStopwords);
		charCount.forEach((k,v)->System.out.println(k+" : "+v));
		
		
		System.out.println();
		System.out.println("------------------Word list with Descending frequency--------------");
		System.out.println();
		
		List<Map.Entry<String, Integer>> mapList = UserOperation.wordCountDescending(dataWithoutStopwords);
		
		for(Map.Entry<String, Integer> entry: mapList) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
//		ArrayList<Character> symbols = FileOperation.readSymbols(symbolsPath);
//		
//		symbols.forEach(s->System.out.println(s));
//		
//		ArrayList<String> stopwords = FileOperation.readStopwords(stopwordsPath);
//		
//		stopwords.forEach(s->System.out.println(s));
	}
}
