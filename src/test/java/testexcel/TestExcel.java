package testexcel;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestExcel {

	String rowValue = "Dinesh";
	String columnValue = "UserName";
	
//	public static void main(String[] args) {
//		FileInputStream fis =new FileInputStream("C:\\Users\\Dinesh.Kanna\\eclipse-workspace\\IGT\\Excel\\Excel.xlsx");
//	    Workbook wb= WorkbookFactory.create(fis);
//	    Sheet sheet = wb.getSheetAt(1);
//	    for (int i = 0; i <=sheet.getLastRowNum(); i++) {
//			if rowValue.equals(sheet.getRow(i).getCell(0).getStringCellValue()) {
//				String value=sheet.getRow(i).getCell(1).getStringCellValue();
//				break;
//			}
//		}
//	}
//	FileInputStream fis =new FileInputStream("File Name");
//    Sheet wb= WorkbookFactory.create(fis).getSheetAt(1);
////    Sheet Sheet1=wb.getSheet("Details2").getActiveCell()
//    for(int i=1;i<=Sheet1.getLastRowNum();i++){
//    if rowValue.equals(Sheet1.getRow(i).getCell(0).getStringCellValue()))
//    {
//    String marks=Sheet1.getRow(i).getCell(1).getStringCellValue();
//
//    System.out.println("values are :- "+marks );
//    break;
//    }
//    }
}
