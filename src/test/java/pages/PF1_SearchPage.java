package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseScripts.PF_base;
public class PF1_SearchPage extends PF_base{
	
	//driver
	public WebDriver driver;
	//page objects
			@FindBy(name="search")
			private WebElement searchTextField;
			
			@FindBy(xpath="//div[@id='search']/descendant::button")
			private WebElement searchButtonField;
			
			@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
			private WebElement searchResultsUnSuccessLabel;
			
			@FindBy(linkText="HP LP3065")
			private WebElement searchResultsSuccessLabel;
			
			// actions on each respective page objects
			public void enterValidORInvalidSearchText(String searchText) {
				searchTextField.clear();
				searchTextField.sendKeys(searchText);
			}
			
	//constructor
		public PF1_SearchPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	
		
	
		public void clickOnLoginSearchButton() {
			searchButtonField.click();
		}
		
		public String getSearchResultsSuccessStatus() {
			String actualText = searchResultsSuccessLabel.getText();
			return actualText;
		}	
		public String getSearchResultsUnSuccessStatus() {
			String actualText = searchResultsUnSuccessLabel.getText();
			return actualText;
		}	
}