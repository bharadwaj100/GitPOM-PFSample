package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.Utilities;

public class PF2_HomePage {
	//driver
	public WebDriver driver;
	//page objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountMenu;
	@FindBy(xpath="//a[text()='Login']")
	private WebElement LoginSubMenu;
	@FindBy(xpath="//a[text()='Logout']")
	private WebElement LogoutSubMenu;
	@FindBy(xpath="//a[text()='Register']")
	private WebElement registerSubMenu;
	public PF2_HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// actions on each respective page objects
	public void clickOnMyAccountMenu() {
		myAccountMenu.click();
	}
	public void clickOnLoginSubMenu() {
		LoginSubMenu.click();
	}
	
	public void clickOnLogoutSubMenu() {
		LogoutSubMenu.click();
	}
	
	public PF1_RegistrationPage clickOnRegisterSubMenu() {
		registerSubMenu.click();
		return null;		
	}
	
	public String getMyAccountMenuValue() {
		return("//span[text()='My Account']");
		}
	
	public String getLoginMenuValue() {
		return("//a[text()='Login']");
		 }
	
}
