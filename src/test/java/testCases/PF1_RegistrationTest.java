package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utilities.Utilities;
import baseScripts.PF_base;
import baseScripts.PF_base_FunctionalInterface;
import baseScripts.PF_base_SupplierInterface;
import pages.PF1_HomePage;
import pages.PF1_RegistrationPage;
public class PF1_RegistrationTest extends PF_base_FunctionalInterface//PF_base_SupplierInterface//PF_base// PF_base_FunctionalInterface//PF_base_SupplierInterface ////PF_base
{
	public WebDriver driverPage;
	
	PF1_HomePage homePage;
	PF1_RegistrationPage registrationPage;
	//Load Configuration in Base class
	public PF1_RegistrationTest() {super();	}
	
	//Initialize Browser in each class setup before start of each class
	@BeforeClass
	public void setup() {
		driverPage =initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		homePage = new PF1_HomePage(driverPage);
		homePage.clickOnMyAccountMenu();
		homePage.clickOnRegisterSubMenu();
		homePage = null;
	}
	@Test(priority=1)
	public void VerifyRegistration_button() {
	registrationPage = new PF1_RegistrationPage(driverPage);
	registrationPage.clickContinue();
	}
	@Test(priority=2, dependsOnMethods="VerifyRegistration_button")
	public void VerifyRegistration_PrivacyWarning(){
	
		System.out.println(registrationPage.getPrivacyWarning());
		Assert.assertEquals(registrationPage.getPrivacyWarning(),"Warning: You must agree to the Privacy Policy!");
	}
	@Test(priority=3, dependsOnMethods="VerifyRegistration_button")
	public void VerifyRegistration_Name_emailWarning(){
		Assert.assertEquals(registrationPage.getFirstNameWarning(),"First Name must be between 1 and 32 characters!");
		Assert.assertEquals(registrationPage.getLastNameWarning(),"Last Name must be between 1 and 32 characters!");
		Assert.assertEquals(registrationPage.getEmailWarning(),"E-Mail Address does not appear to be valid!");
	}
	
	@Test(priority=4)
	public void VerifyRegisterAccountWithAllFields(){
		registrationPage.enterFirstName("Arun");
		registrationPage.enterLastName("Motoori");
		registrationPage.enterEmail(Utilities.generateTimeStampWithEmail());
		registrationPage.enterTelephone("1234567890");
		registrationPage.enterPassword("12345");
		registrationPage.enterConfirmPassword("12345");
		registrationPage.checkAgreeCheckBox();
		registrationPage.clickContinue();
		String SucessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(SucessHeading,"Your Account Has Been Created!","Account Sucess Page");
		registrationPage=null;
	}
	@Test(priority=5)
	public void VerifyLogOut(){
		homePage = new PF1_HomePage(driverPage);
		homePage.clickOnMyAccountMenu();
		homePage.clickOnLogoutSubMenu();
		String LogOutHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(LogOutHeading,"Account Logout","Account LogOut Page");
	}
	@AfterClass
	public void teardown() {
		driver.manage().deleteAllCookies();
		driver.quit();}
	}
