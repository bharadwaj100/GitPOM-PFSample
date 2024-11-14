package baseScripts;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;



//import Factory.GooglePage;

import java.util.function.Function;
import java.util.function.Supplier;
public class PF_base_FunctionalInterface {
	public WebDriver driver ;
	public Properties prop;
	public Properties testDataProp;

	private Map<String,Function<String,WebDriver>> driverMap=null;
	private Function<String,WebDriver> CHRM =null;
	private Function<String,WebDriver> FF =null;
	private Function<String,WebDriver>EDGE=null;
	public PF_base_FunctionalInterface()
	{
		//config properties
		prop =new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\configs\\PF_config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
		e.printStackTrace();}
		//testData properties
		testDataProp=new Properties();
		File testDataPropFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\PF_testData.properties");
	
		try {
			FileInputStream fis = new FileInputStream(testDataPropFile);
			testDataProp.load(fis);
		}catch(Throwable e) {
		e.printStackTrace();}
		CHRM =(d)-> new ChromeDriver() ;
		FF =(d)-> new FirefoxDriver();
		EDGE=(d)-> new EdgeDriver();
		driverMap = new HashMap<>();
		driverMap.put("chrome",CHRM);
		driverMap.put("firefox",FF);
		driverMap.put("edge",EDGE);		
	}
	
	public WebDriver initializeBrowserAndOpenApplicationUrl(String browserName)
	{
		
		driver  =driverMap.get(browserName).apply(browserName);
		EdgeOptions options = new EdgeOptions();
		options.addArguments("disable-popup-blocking");
		options.addArguments("disable-extensions");
		driver.get(prop.getProperty("testSiteURL"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.valueOf(prop.getProperty("pageLoadWait"))));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(prop.getProperty("implicitWait"))));
		return driver;
		
	}
	
	}

		
	
