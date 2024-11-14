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
import java.util.Map;
import java.util.function.Supplier;
public class PF_base_SupplierInterface {
	public Map<String, Supplier<WebDriver>> driverMap = null;
	public WebDriver driver ;
	public Properties prop;
	public Properties testDataProp;
	public String browserName;
	public PF_base_SupplierInterface()
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
		driverMap = new HashMap<>();
		driverMap.put("chrome",this::CreateChromeDriver);
		driverMap.put("firefox",this::CreateFireFoxDriver);
		driverMap.put("edge",this::CreateEdgeDriver);
	}
private WebDriver CreateChromeDriver() {return new ChromeDriver();}
private WebDriver CreateFireFoxDriver(){return new FirefoxDriver();}	
private WebDriver CreateEdgeDriver(){return new EdgeDriver();}
public WebDriver initializeBrowserAndOpenApplicationUrl(String browserName)
	{
		//WebDriver driver =null;
		//Supplier<WebDriver> driverSupplier = driverMap.getOrDefault(browserName.toLowerCase().trim(),this::createChromeDriver);
		Supplier<WebDriver> driverSupplier = driverMap.get(browserName);
		driver  =driverSupplier.get();
		System.out.print(browserName.trim());
//		EdgeOptions options = new EdgeOptions();
//		options.addArguments("disable-popup-blocking");
//		options.addArguments("disable-extensions");
		driver.get(prop.getProperty("testSiteURL"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.valueOf(prop.getProperty("pageLoadWait"))));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(prop.getProperty("implicitWait"))));
		return driver;
		
	}
	
}
	
