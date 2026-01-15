package com.cloud.wordcount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class UserOperation {

	public static TreeSet<String> uniqueWordList(String dataWithoutStopwords) {

		String[] str = dataWithoutStopwords.split(" ");

		ArrayList<String> list = new ArrayList<>(Arrays.asList(str));

		TreeSet<String> unique = new TreeSet<>(list);

		return unique;
	}

	public static Map<String, Integer> wordCount(String dataWithoutStopwords) {

		String[] str = dataWithoutStopwords.split(" ");

		ArrayList<String> list = new ArrayList<>(Arrays.asList(str));

		Map<String, Integer> map = new TreeMap<>();

		for (String string : list) {
			map.put(string, Collections.frequency(list, string));
		}

		return map;
	}

	public static List<Map.Entry<String, Integer>> wordCountDescending(String dataWithoutStopwords) {

		String[] str = dataWithoutStopwords.split(" ");

		ArrayList<String> list = new ArrayList<>(Arrays.asList(str));

		Map<String, Integer> map = new TreeMap<>();

		for (String string : list) {
			map.put(string, Collections.frequency(list, string));
		}
		
		List<Map.Entry<String, Integer>> mapList = new ArrayList<>(map.entrySet());
		mapList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

		return mapList;
	}

	public static Map<Character, Integer> characterCount(String dataWithoutStopwords) {

		char[] ch = dataWithoutStopwords.toCharArray();

		ArrayList<Character> list = new ArrayList<>();

		for (Character character : ch) {
			list.add(character);
		}

		Map<Character, Integer> charCount = new TreeMap<>();

		// ASCII values a-65, b=66

		for (int i = 97; i <= 122; i++) {
			int frequency = Collections.frequency(list, (char) i);
			charCount.put((char) i, frequency);
		}

		for (int i = 65; i <= 90; i++) {
			int frequency = Collections.frequency(list, (char) i);
			charCount.put((char) i, frequency);
		}

		return charCount;

	}
}
