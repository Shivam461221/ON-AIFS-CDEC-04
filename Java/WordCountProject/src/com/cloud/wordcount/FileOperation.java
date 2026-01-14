package com.cloud.wordcount;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperation {

	public static String readInputFile(String filePath) throws IOException {
		File file = new File(filePath);

		Scanner sc = new Scanner(file);
		String inputData = "";
		while (sc.hasNext()) {
			inputData = inputData.concat(sc.next().concat(" "));
		}

		return inputData.toLowerCase();
	}

	public static ArrayList<Character> readSymbols(String filePath) throws IOException {
		File file = new File(filePath);

		Scanner sc = new Scanner(file);

		ArrayList<Character> symbols = new ArrayList<>();

		while (sc.hasNext()) {
			symbols.add(sc.next().charAt(0));
		}

		return symbols;
	}

	public static ArrayList<String> readStopwords(String filePath) throws IOException {
		File file = new File(filePath);

		Scanner sc = new Scanner(file);

		ArrayList<String> stopwords = new ArrayList<>();

		while (sc.hasNext()) {
			stopwords.add(sc.next());
		}

		return stopwords;
	}

}
