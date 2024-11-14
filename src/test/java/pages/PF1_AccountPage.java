package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseScripts.PF_base;
public class PF1_AccountPage //extends PF_base
{
	// Locators for the login page elements
   //    private By usernameField = By.id("username");
  //    private By passwordField = By.id("password");
  //    private By loginButton = By.id("loginButton");
	//driver
	public WebDriver driver;
	//page objects
	@FindBy(linkText="Edit your account information")
	private WebElement  loginSuccessLabel;

	public PF1_AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyLoginSuccessLabelStatus() {
		return loginSuccessLabel.isDisplayed();
	}
}
