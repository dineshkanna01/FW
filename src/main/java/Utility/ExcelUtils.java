package Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	//This method is use to get inputs from excel sheet

	public static String getTestData(int rowIndex,int colInddex,String sheetName) throws EncryptedDocumentException, IOException, InvalidFormatException {
		    String data;
			FileInputStream Myfile = new FileInputStream("C:\\Users\\Dinesh.Kanna\\eclipse-workspace\\IGT\\Excel\\Excel.xlsx");
			Sheet MySheet = WorkbookFactory.create(Myfile).getSheet(sheetName);
			try {
			data=MySheet.getRow(rowIndex).getCell(colInddex).getStringCellValue();
			} catch (Exception e) {
				
		Long A1=(new Double(MySheet.getRow(rowIndex).getCell(colInddex).getNumericCellValue())).longValue();
				
			data=A1+""; 
		 }  
			return data;
		}

}
