package baseScripts;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
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

public class PF_base {
	public WebDriver driver ;
	public Properties prop;
	public Properties testDataProp;
	public PF_base()
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
		
	}
	
	public WebDriver initializeBrowserAndOpenApplicationUrl(String browserName) {
	
		if(browserName.equals("chrome"))
		{
		   driver = new ChromeDriver();
		   }
		else if(browserName.equals("firefox"))
		{
		   driver = new FirefoxDriver();
		   }
		else if(browserName.equals("edge"))
		{
		   EdgeOptions options = new EdgeOptions();
		   options.addArguments("disable-popup-blocking");
		   options.addArguments("disable-extensions");
		   driver = new EdgeDriver(options);
		   }
		driver.get(prop.getProperty("testSiteURL"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.valueOf(prop.getProperty("pageLoadWait"))));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(prop.getProperty("implicitWait"))));
		return driver;
		}
	
	
	}

		
	
