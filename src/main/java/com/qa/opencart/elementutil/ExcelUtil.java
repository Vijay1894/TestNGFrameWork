package com.qa.opencart.elementutil;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	
	public static final String TEST_DATA_SHEET_PATH = "./src/test/resources/TestData/opencarttestdata.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	private static Object data[][];
	
	
	public static Object[][] getTestData(String sheetname)
	{
		
		FileInputStream ip;
		
		try {
		ip = new FileInputStream(TEST_DATA_SHEET_PATH);
		book =	WorkbookFactory.create(ip);
		sheet = book.getSheet(sheetname);
		
		data = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i=0;i<sheet.getLastRowNum();i++)
		{
			for (int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			{ 
				data[i][j]= sheet.getRow(i+1).getCell(j).toString();
			}
		}
			
		} catch (EncryptedDocumentException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return data;
		
		
		
		
		
		
	}

}
