package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.github.javafaker.Faker;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait = null;
	Faker faker = new Faker();
	String computerName = faker.name().firstName();
	static String newComputerName = null;
	String introducedDate = "1992-12-01";
	String discontinuedDate = "2003-08-09";
	
	public HomePage(WebDriver driver) { 
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	 /**
	 * Find the element locator.
	 */
	
	@FindBy(className = "topbar") 	 
	private WebElement homepageTitle;
	
	@FindBy(id = "add") 	 
	private WebElement addNewButton;
	
	@FindBy(xpath = "//*[@id=\"main\"]/h1") 	 
	private WebElement newRecordTitle;
	
	@FindBy(id = "name") 	 
	private WebElement computerNameField;
	
	@FindBy(id = "introduced") 	 
	private WebElement introducedDateField;
	
	@FindBy(id = "discontinued") 	 
	private WebElement discontinuedDateField;
	
	@FindBy(id = "company") 	 
	private WebElement chooseCompany;
	
	@FindBy(css = ".btn.primary") 	 
	private WebElement createThisComputerButton;
	
	@FindBy(css = ".alert-message.warning") 	 
	private WebElement alertSuccesssMsg;
	
	@FindBy(id = "searchbox") 	 
	private WebElement searchboxField;
	
	@FindBy(id = "searchsubmit") 	 
	private WebElement searchButton;
	
	@FindBy(xpath = "//*[@id=\"main\"]/table/tbody/tr[1]/td") 	 
	private List<WebElement> tableColumns;
	
	@FindBy(className = "well") 	 
	private WebElement noRecordMessage;
	
	@FindBy(css = ".btn.danger") 	 
	private WebElement deleteThisComputerButton;
	
	 /**
	 * Utility functions.
	 */
	private void click(WebElement element)
	{
		WebElement visibleElement = waitForElementToAppear(element);
		WebElement clickableElement = waitForElementToClickable(visibleElement);
		clickableElement.click();
	}
	
	private void enterValue(WebElement element, String value) throws InterruptedException
	{
		element.clear();
		element.sendKeys(value);
	}
	
	private void selectValue(WebElement element, String value) {
		Select selector = new Select(element);
		selector.selectByVisibleText(value);		
	}
	
	private WebElement waitForElementToClickable(WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver,20);
		 WebElement clickableElement =  wait.until(ExpectedConditions.elementToBeClickable(element));
		 return clickableElement;
	}
	
	private WebElement waitForElementToAppear(WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver,20);
		 WebElement activeElement =  wait.until(ExpectedConditions.visibilityOf(element));
		 return activeElement;
	}
	
	/** 
	 * CRUD operation function
	 */
	
	public void validateHomePage() {
		Assert.assertEquals(homepageTitle.getText(), "Play sample application — Computer database", "Homepage title is incorrect");
	}
	
	public void addNewComputerButton() {
		click(addNewButton);
	}

	public void validateNewRecordPage() {
		Assert.assertEquals(newRecordTitle.getText(), "Add a computer", "New record page title is incorrect");
	}
	
	public void enterComputerDetail() throws InterruptedException {
		waitForElementToClickable(createThisComputerButton);
		enterValue(computerNameField, computerName);	
		enterValue(introducedDateField, introducedDate);
		enterValue(discontinuedDateField, discontinuedDate);
		selectValue(chooseCompany, "Nokia");
		newComputerName = computerNameField.getAttribute("value");
	}
	
	public void addNewRecord() throws InterruptedException {
		click(createThisComputerButton);
	}
	
	public void validateAlertMessage(String alertType ) {
		if(alertType.equalsIgnoreCase("new record"))
			Assert.assertEquals(alertSuccesssMsg.getText(), "Done! Computer "+ newComputerName +" has been created", "Newly added record alert message is incorrect");
		else if(alertType.equalsIgnoreCase("update record"))
			Assert.assertEquals(alertSuccesssMsg.getText(), "Done! Computer "+ newComputerName +" has been updated", "Unable to update record");
		else if(alertType.equalsIgnoreCase("delete record")) 
			Assert.assertEquals(alertSuccesssMsg.getText(), "Done! Computer has been deleted", "Unable to delete record");
	}
	
	public void searchRecord() throws InterruptedException {
		enterValue(searchboxField, newComputerName);
		click(searchButton);
	}
	
	public void validateSearchRecord() throws InterruptedException {
		Assert.assertEquals(tableColumns.get(0).getText(), newComputerName, "Updated record is not found");
	}
	
	public void openNewRecord() throws InterruptedException {
		click(tableColumns.get(0).findElement(By.tagName("a")));
	}
	
	public void deleteRecord() {
		click(deleteThisComputerButton);
	}

	public void validateDeleteAlertMsg() {
		Assert.assertEquals(alertSuccesssMsg.getText(), "Done! Computer has been deleted", "Unable to delete record");
	}

	public void validateSearchOfDeleteRecord() {
		Assert.assertEquals(noRecordMessage.getText(), "Nothing to display", "Deleted record is visible in table");
	}

}
