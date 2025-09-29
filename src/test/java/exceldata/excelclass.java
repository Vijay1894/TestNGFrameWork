package exceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class excelclass {
	

	 public static void main(String[] args)
	 {
		 try {
			FileInputStream fis = new FileInputStream("src\\test\\resources\\TestData\\opencarttestdata.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet("Product");
			
			int rows = sheet.getLastRowNum();
			
			for (int i=1;i<=rows;i++)
			{
				Row row = sheet.getRow(i);
				
				String searchKey= row.getCell(0).getStringCellValue();
				String productname= row.getCell(1).getStringCellValue();
				int ExpectedImagesCount =row.getCell(2).getCellType();
			
				System.out.println(searchKey+" "+productname+" "+ExpectedImagesCount);
			}
	
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (InvalidFormatException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
	 }

}
