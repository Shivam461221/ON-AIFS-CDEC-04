package com.cloud.inputoutput;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BinaryFiles {

	public static void main(String[] args) {
//		try {
//			FileInputStream fis = new FileInputStream("C:\\ON-AIFS-CDEC-04\\Java\\movie.mp4");
//			FileOutputStream fos = new FileOutputStream("C:\\ON-AIFS-CDEC-04\\Java\\copy_movie.mp4");
//			
//			int ch;
//			
//			while((ch=fis.read())!=-1) {
//				fos.write(ch);	
//			}
//			System.out.println("Data copied");
//			
//			fis.close();
//			fos.close();
//
//		}
//		catch(Exception e) {
//			System.out.println("Error: "+e);
//		}

		try (FileInputStream fis = new FileInputStream("C:\\ON-AIFS-CDEC-04\\Java\\movie.mp4");
			FileOutputStream fos = new FileOutputStream("C:\\ON-AIFS-CDEC-04\\Java\\copy_movie.mp4");) {

			int ch;

			while ((ch = fis.read()) != -1) {
				fos.write(ch);
			}
			System.out.println("Data copied");

			

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
