package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseScripts.PF_base;

public class PF1_RegistrationPage extends  PF_base {
	//POM code sample with out pageFactory
	// Locators for the login page elements
	   //    private By usernameField = By.id("username");
	  //    private By passwordField = By.id("password");
	  //    private By loginButton = By.id("loginButton");
//		public void enterUsername(String username) {
//	        WebElement usernameElement = driver.findElement(usernameField);
//	        usernameElement.sendKeys(username);
//	    }
	//driver
	public WebDriver driver;
	//page objects
		@FindBy(id="input-firstname")
		private WebElement inputFirstnameTextField;
		@FindBy(id="input-lastname")
		private WebElement inputLastnameTextField;
		@FindBy(id="input-email")
		private WebElement inputEmailTextField;
		@FindBy(id="input-telephone")
		private WebElement inputTelephoneTextField;
		@FindBy(id="input-password")
		private WebElement inputPasswordTextField;
		@FindBy(id="input-confirm")
		private WebElement inputConfirmTextField;
		@FindBy(name="agree")
		private WebElement checkField;
		@FindBy(xpath="//input[@value='Continue']")
		private WebElement buttonContinueField;
		
		 // warning messages
		@FindBy(xpath="//div[contains(text(),'Warning')]")
		private WebElement PrivacyPolicyWarning;
		@FindBy(xpath="//div[contains(text(),'First Name must be')]")
		private WebElement firstNameWarning;
		@FindBy(xpath="//div[contains(text(),'Last Name must be')]")
		private WebElement lastNameWarning;
		@FindBy(xpath="//div[contains(text(),'does not appear')]")
		private WebElement emailWarning;
	
	//constructor
	public PF1_RegistrationPage(WebDriver driver) {
		//Variables 
			this.driver = driver;
			PageFactory.initElements(driver, this);
			}
	
	
	// actions on each respective page objects
		public void enterFirstName(String firstName) {
			inputFirstnameTextField.sendKeys(firstName);
		}
		public void enterLastName(String lastName) {
			inputLastnameTextField.sendKeys(lastName);
		}
		public void enterEmail(String email) {
			inputEmailTextField.sendKeys(email);
		}
		public void enterTelephone(String telephone) {
			inputTelephoneTextField.sendKeys(telephone);
		}
		public void enterPassword(String password) {
			inputPasswordTextField.sendKeys(password);
		}
		public void enterConfirmPassword(String password) {
			inputConfirmTextField.sendKeys(password);
		}
		public String getPrivacyWarning() {
			return PrivacyPolicyWarning.getText();
		}
		public String getFirstNameWarning() {
			return firstNameWarning.getText();
		}
		public String getLastNameWarning() {
			return lastNameWarning.getText();
		}
		public String getEmailWarning() {
			return emailWarning.getText();
		}
		public void checkAgreeCheckBox() {
		checkField.click();}
		
		public void clickContinue() {
		buttonContinueField.click();}
	}
