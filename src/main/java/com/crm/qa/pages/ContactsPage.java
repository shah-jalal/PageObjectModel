package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{

	// Page Factory -->> Object Repository:
	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;

	@FindBy(name = "title")
	WebElement option;
	
	@FindBy(id = "first_name")
	WebElement firstName;
	
	@FindBy(id = "surname")
	WebElement lastName;
	
	@FindBy(name = "client_lookup")
	WebElement company;
	
	@FindBy(xpath = "//input[@type = 'submit' and @value = 'Save']")
	WebElement saveBtn;
	
	// Initializing the Page Objects:
	public ContactsPage(){
			PageFactory.initElements(driver, this); // 'this' -->> current class object
		}

	// Actions:
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name){
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
				+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
	}
	
//	public void createNewContacts(String title, String ftName, String ltName, String comp) {
//		//WebElement option = driver.findElement(By.name("title"));
//		Select select = new Select(option);
//		select.selectByVisibleText(title);
//		
//		firstName.sendKeys(ftName);
//		lastName.sendKeys(ltName);
//		company.sendKeys(comp);
//		saveBtn.click();
//	}
	
	public void createNewContacts(String title, String ftName, String ltName, String comp) {
		Select select = new Select(option);
		select.selectByVisibleText(title);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + ftName + "');", firstName);
		js.executeScript("arguments[0].setAttribute('value', '" + ltName + "');", lastName);
		js.executeScript("arguments[0].setAttribute('value', '" + comp + "');", company);
		js.executeScript("arguments[0].click();", saveBtn);
	}

} //Class -->> ContactsPage
