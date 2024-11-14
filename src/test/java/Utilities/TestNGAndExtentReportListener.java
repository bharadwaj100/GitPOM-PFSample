package Utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseScripts.PF_base;

//log4j10-10-2024
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class TestNGAndExtentReportListener extends PF_base implements ITestListener,ISuiteListener {
	Logger Logger = LogManager.getLogger((TestNGAndExtentReportListener.class));

	String destinationScreenshotPath;
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName ;
	//WebDriver driver;
	@Override
	public void onStart(ISuite suite) {
		driver =initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		extentReport = ExtentReporter.generateExtentReport();
		String suitename= suite.getName();		
		Logger.trace("This is Log Trace Message");
		Logger.info(suitename+ "suite started");
		Logger.throwing(null);
		Logger.log(null, testName);
		Logger.log(null, suite.getName()+"started");
		
		//seleniumLogger.seleniumLogger();
	}
	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport = ExtentReporter.generateExtentReport();
		//log4j
		Logger.trace("This is Log Trace Message");
		Logger.info("context started");
		Logger.throwing(null);
		Logger.log(null, testName);
	}
	@Override
	public void onTestStart(ITestResult result) {
		
		testName = result.getName();
		extentTest=extentReport.createTest(testName);
		extentTest.log(Status.INFO,testName +"  started executing");
		//extentReport.flush();
		//log4j
//		Logger.trace("This is Trace Message");
//		Logger.info(testName+" This is from Log4j started");
//	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
		extentTest.log(Status.PASS, testName+" got successfully executed");
		
		//log4j
		Logger.trace("This is Trace Message");
		Logger.info(testName+" This is from Log4j Passed");	
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
			testName=result.getName();
			
			try{
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			}catch(Exception e) {
				e.printStackTrace();
			}
			destinationScreenshotPath =Utilities.takeScreenshot(driver, testName);
			extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
			testName = result.getName();
			extentTest.log(Status.INFO, result.getThrowable());
			extentTest.log(Status.FAIL, testName+" Test got failed");
		
			//log4j
			Logger.trace("This is Log Trace Message");
			Logger.info(testName+" This is from Log4j Failure");
			}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" Test Skipped");
	
		//log4j
		Logger.trace("This is Log Trace Message");
		Logger.info(testName+" This is from Log4j skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
				extentReport.flush();
			}
	@Override
	public void onFinish(ISuite suite) {
		String suitename= suite.getName();			
		Logger.info( suitename+"suite Finished");
		extentReport.flush();
	
	}

	

}
