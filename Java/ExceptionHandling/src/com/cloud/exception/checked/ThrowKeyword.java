package com.cloud.exception.checked;

import java.io.IOException;

import com.cloud.exception.CustomException;

public class ThrowKeyword {

	public void readFile() throws CustomException {
		throw new CustomException("File not found");
	}

	public static void main(String[] args) {
		ThrowKeyword io = new ThrowKeyword();

		// Exceptional condition
		try {
			io.readFile();

		} catch (Exception e) {
			System.out.println(e+" ");
		}
	}

}
