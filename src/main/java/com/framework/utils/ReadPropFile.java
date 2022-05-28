package com.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropFile {
	
	//This class will have all the methods to deal with Property file data

	
	public static Properties readData(String filename) {
		Properties prop = new Properties();  // to read properties initializing prop class
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Config\\"+filename); // to read /recognize files in java
			prop.load(fis); // loading all the data into prop variable
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;		
	}

}
