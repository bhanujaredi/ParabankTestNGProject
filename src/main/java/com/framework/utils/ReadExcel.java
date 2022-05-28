package com.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	//This class will have all the methods to deal with Excel

	
	public static String[][] readData(String workbook, String sheet){
		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+workbook); //to read file
			XSSFWorkbook wb = new XSSFWorkbook(fis); // to read/recognize excel file
			XSSFSheet sh = wb.getSheet(sheet); //to read sheet
			int totalrows = sh.getPhysicalNumberOfRows(); 
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();
			data = new String [totalrows-1][totalColumns];
			
			for(int i=1;i<totalrows;i++) {
				for(int j=0;j<totalColumns; j++) {
					data[i-1][j] = sh.getRow(i).getCell(j).getStringCellValue();
				}
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return data;
	}

}
 