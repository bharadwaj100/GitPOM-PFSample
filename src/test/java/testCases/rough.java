package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class rough {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop =new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\configs\\ver5_config.properties");
		//C:\SFW-Demo\tutorialsNinjaProj\src\test\java\configs
		
		System.out.println(propFile.toString());
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
			System.out.println(prop.getProperty("browserName"));
			System.out.println(prop.getProperty("testSiteURL"));
			System.out.println(prop.getProperty("validLogin"));
			System.out.println(prop.getProperty("validPassword"));
		}catch(Throwable e) {
		e.printStackTrace();}
		System.out.println("==========================================");
		Object[][] Mydata =getTestDataFromExcel("Login");
		for(int i=0;i<=2;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(Mydata[i][j].toString()+" ");
			}
			System.out.println();
		}
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName)
	{

		
	File excelFile =new File(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\testData.xlsx");

	FileInputStream fisExcel=null;
	try {
		fisExcel = new FileInputStream(excelFile);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	XSSFWorkbook workbook=null;
	try {
		workbook = new XSSFWorkbook(fisExcel);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	XSSFSheet sheet = workbook.getSheet(sheetName);
	int rows = sheet.getLastRowNum();
	int cols = sheet.getRow(0).getLastCellNum();
	Object[][] data = new Object[rows][cols];
	for(int i=0;i<rows;i++)
	{
	XSSFRow row = sheet.getRow(i+1);

	  for(int j=0;j<cols;j++)
	  {
		  
	     XSSFCell cell = row.getCell(j);
	     CellType cellType = cell.getCellType();
	
	     switch(cellType)
	     {
	     case STRING:
	        data[i][j]=cell.getStringCellValue();
	   
	        break;
	 	case NUMERIC:
	        data[i][j]=Integer.toString((int)cell.getNumericCellValue());
	
	        break;
		case BOOLEAN:
	        data[i][j]=cell.getBooleanCellValue();
	   
	        break;
	        }}}
	return data;}
	}



