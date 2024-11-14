package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PF1_HomePage;
import pages.PF1_SearchPage;
import baseScripts.PF_base;
import baseScripts.PF_base_FunctionalInterface;
import baseScripts.PF_base_SupplierInterface;

public class PF1_SearchTest extends PF_base_FunctionalInterface//PF_base_SupplierInterface//PF_base//PF_base_FunctionalInterface//PF_base_SupplierInterface //PF_base_FunctionalInterface//PF_base
{
	//Variables 	
	//public WebDriver driver;
	PF1_SearchPage searchPage;
	//Load Configuration in Base class
	public PF1_SearchTest() {super();}
	//Initialize Browser in each class setup before start of each class
	@BeforeClass
	public void setup() {
	driver =initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
	searchPage = new PF1_SearchPage(driver);
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct(){
	searchPage.enterValidORInvalidSearchText("HP");
	searchPage.clickOnLoginSearchButton();
	String actualText=searchPage.getSearchResultsSuccessStatus();
	Assert.assertEquals("HP LP3065",actualText);
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct(){
	searchPage.enterValidORInvalidSearchText("Honda");
	searchPage.clickOnLoginSearchButton();
	String actualText=searchPage.getSearchResultsUnSuccessStatus();
	String expectedText= "There is no product that matches the search criteria.";
	Assert.assertEquals(actualText,expectedText);
	}
	
	@Test(priority=3)
	public void verifySearchWithInvalidProduct1(){
	searchPage.enterValidORInvalidSearchText("Fail A Test Demo");
	searchPage.clickOnLoginSearchButton();
	String actualText=searchPage.getSearchResultsUnSuccessStatus();
	String expectedText= "Fail-There is no product that matches the search criteria-FailDemo.";
	Assert.assertEquals(actualText,expectedText);
	}
	
	@Test(priority=4,dependsOnMethods={"verifySearchWithInvalidProduct1"})
	public void verifySearchWithInvalidProduct2(){
	searchPage.enterValidORInvalidSearchText("Skip A Test Demo");
	searchPage.clickOnLoginSearchButton();
	String actualText=searchPage.getSearchResultsUnSuccessStatus();
	String expectedText= "There is no product that matches the search criteria.";
	Assert.assertEquals(actualText,expectedText);
	}
	@AfterClass
	public void teardown() {
		//driver.manage().deleteAllCookies();
		driver.quit();}
	}
