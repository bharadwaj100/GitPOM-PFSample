package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseScripts.PF_base;
public class PF1_LoginPage extends PF_base
{
	//driver
	public WebDriver driver;
	
	//page objects
		@FindBy(id="input-email")
		private WebElement emailTextField;
		@FindBy(id="input-password")
		private WebElement  passwordTextField;
		@FindBy(xpath="//input[@value='Login']")
		private WebElement  loginButton;
		@FindBy(xpath="//div[contains(text(),'Warning')]") 
		private WebElement  loginUnSuccessLabel;
		
	//constructor
	public PF1_LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// actions on each respective page objects
	public void enterEmailText(String email) {
		emailTextField.sendKeys(email);
	}
	public void enterPasswordText(String pass) {
		passwordTextField.sendKeys(pass);
	}
	public void clickOnLoginButton() {
		loginButton.click();
	}
	public boolean verifyLoginUnSuccessLabelStatus() {
		return loginUnSuccessLabel.isDisplayed();
	}
	
		
}

