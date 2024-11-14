package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
public class ExtentReporter
{
	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReport = new ExtentReports();
		String temp=Utilities.generateOnlyTimeStamp();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\"+temp+".html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setDocumentTitle("TutorialsNinjaProj-POM-PageFactory-Report");
		sparkReporter.config().setReportName("TutorialsNinjaProj-POM-PageFactory Automation Results");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		//========== config========
		 Properties prop =new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\configs\\PF_config.properties");		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
			}catch(Throwable e)
		 {
			e.printStackTrace();}
		extentReport.setSystemInfo("TestSiteURL",prop.getProperty("testSiteURL"));
		extentReport.setSystemInfo("BrowserName",prop.getProperty("browserName"));
		extentReport.setSystemInfo("UserName",prop.getProperty("validLogin"));
		extentReport.setSystemInfo("Password",prop.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name",System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		return extentReport;
	}
}
