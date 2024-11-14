package Utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
public static String takeScreenshot(WebDriver driver ,String testName) 
{
	File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String destinationScreenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+testName+Utilities.generateOnlyTimeStamp()+".png";
	try{
	FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
	}catch(IOException e) {
	e.printStackTrace();
	}
	return destinationScreenshotPath;
}
public static String generateTimeStampWithEmail()
{
Date date = new Date();
String timestamp=date.toString().replace(" ","_").replace(":","_");
return "amotoori"+timestamp+"@rediffmail.com";
}
public static String generateOnlyTimeStamp()
{
Date date = new Date();
String timestamp=date.toString().replace(" ","_").replace(":","_");
return timestamp;
}
public static void WaitUntillPresentAndClick(WebDriver driver,String ele)
{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	WebElement wele =driver.findElement(By.xpath(ele));
	wait.until(ExpectedConditions.elementToBeClickable(wele));
	wele.click();
	wait =null;
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


