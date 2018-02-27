package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	// Page Factory -->> Object Repository
	@FindBy(xpath = "//td[contains(text(),'User: Naveen K')]")
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(), 'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[contains(text(), 'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath = "//a[contains(text(), 'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath = "//a[contains(text(), 'New Contact')]")
	WebElement newContactLink;
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this); // 'this' -->> current class object
	}
	
	//Actions:
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink() {
		contactsLink.click();
		return new TasksPage();
	}
	
	public void clickOnNewContactLink() {
		Actions action = new Actions(driver);
		// Mouse-over/Mouse-hover to Contacts Link
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}
	
	public void clickOnNewContact() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", newContactLink);
	}
	
	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();
	}

}
