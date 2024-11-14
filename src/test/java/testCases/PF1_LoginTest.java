package testCases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.Utilities;


import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.PF1_AccountPage;
import pages.PF1_HomePage;
import pages.PF1_LoginPage;
import baseScripts.PF_base;
import baseScripts.PF_base_FunctionalInterface;
import baseScripts.PF_base_SupplierInterface;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
public class PF1_LoginTest extends PF_base_FunctionalInterface//PF_base_SupplierInterface//PF_base//PF_base_FunctionalInterface//// //
{
	
	//Variables 
	String email="";
	String pass ="";
	public WebDriver driver;
	PF1_HomePage homePage;
	public PF1_LoginTest() {super();}
	
	
	@BeforeClass
	public void setup() {
	driver =initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
	homePage = new PF1_HomePage(driver);
	
	}
	
	
	@Test(priority=2,dataProvider="LoginData")
	public void verifyLoginWithValidCredentials(String email,String pass) {
      PF1_LoginPage loginPage = new  PF1_LoginPage(driver);
      PF1_AccountPage accountPage  = new PF1_AccountPage(driver);
      homePage.clickOnMyAccountMenu();
 	  homePage.clickOnLoginSubMenu();
      loginPage.enterEmailText(email);
      loginPage.enterPasswordText(pass);
      loginPage.clickOnLoginButton();

	  Assert.assertTrue(accountPage.verifyLoginSuccessLabelStatus(),"validating the message");
	  homePage.clickOnMyAccountMenu();
	  homePage.clickOnLogoutSubMenu();
	  
	}	
	
	@DataProvider(name="LoginData")
	public Object[][] LoginData()
	{
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=1)
	public void verifyLoginWithInvalidCredentials()
	{
		PF1_LoginPage loginPage = new  PF1_LoginPage(driver);
		homePage.clickOnMyAccountMenu();
		homePage.clickOnLoginSubMenu();	
		
		loginPage.enterEmailText(testDataProp.getProperty("invalidEmail"));
	    loginPage.enterPasswordText(testDataProp.getProperty("invalidPass"));
	    loginPage.clickOnLoginButton();

		Assert.assertTrue(loginPage.verifyLoginUnSuccessLabelStatus(),"validating the UnSucessfulMessage");
	}	
	
	@AfterClass
	public void teardown() {
		driver.quit();}
}
	