package com.cloud.wordcount;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DataOperation {
	
	public static String removeSymbols(String rawData, String symbolsPath) throws IOException{
		
		char[] ch = rawData.toCharArray();
		
		ArrayList<Character> list = new ArrayList<>();
		
		for(int i=0; i<ch.length; i++) {
			list.add(ch[i]);
		}
		
		ArrayList<Character> symbols = FileOperation.readSymbols(symbolsPath);
		
		list.removeAll(symbols);
		
		String dataWithoutSymbols = "";
		
		for(Character character: list) {
			dataWithoutSymbols = dataWithoutSymbols.concat(String.valueOf(character));
		}
		
		return dataWithoutSymbols;
	}
	
	public static String removeStopwords(String dataWithoutSymbols, String stopwordsPath) throws IOException{
		
		String[] str = dataWithoutSymbols.split(" ");
		
		ArrayList<String> list = new ArrayList<>(Arrays.asList(str));
		
		ArrayList<String> stopwords = FileOperation.readStopwords(stopwordsPath);
		
		list.removeAll(stopwords);
		
		String dataWithoutStopwords = "";
		
		for(String string: list) {
			dataWithoutStopwords = dataWithoutStopwords.concat(string).concat(" ");
		}
		
		return dataWithoutStopwords;
	}
	
	
		
	
}
