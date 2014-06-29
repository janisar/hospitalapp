package com.hospital.spring.mvc.data;




public class File {
	
	private static String fileName = "";

	public static String getFileName() {
		return fileName;
	}
	
	public static void setFileName(String file) {
		File.fileName = file;
	}
	
}
