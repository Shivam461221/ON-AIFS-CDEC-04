package com.cloud.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataOperation {

	public static String removeSymbols(String inputData, String symbolsFile) throws IOException {

		char[] ch = inputData.toCharArray();

		ArrayList<Character> list = new ArrayList<>();

		for (int i = 0; i < ch.length; i++) {
			list.add(ch[i]);
		}

		ArrayList<Character> symbols = FileOperations.readSymbols(symbolsFile);

		list.removeAll(symbols);

		inputData = "";

		for (Character character : list) {
			inputData = inputData.concat(String.valueOf(character));
		}

		return inputData;
	}
	
	public static String removeStopwords(String inputFile, String stopwordFile) throws IOException{
		
		String[] str = inputFile.split(" ");
		
		ArrayList<String> list = new ArrayList<>(Arrays.asList(str));
		
		ArrayList<String> stopwords = FileOperations.readStopwords(stopwordFile);
		
		list.removeAll(stopwords);
		
		inputFile = "";
		
		for(String string: list) {
			inputFile = inputFile.concat(string).concat(" ");
		}
		
		return inputFile;
	}
}
