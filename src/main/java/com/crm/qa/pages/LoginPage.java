package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	// Page Factory -->> Object Repository:
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath="//button[contains(test(),'Sign Up']")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@class, 'img-responsive')]")
	WebElement crmLogo;
	
	//Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this); // 'this' -->> current class object
	}
	
	//Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMlogo() {
		return crmLogo.isDisplayed();
	}

	public HomePage login(String usrName, String pssWord) {
		username.clear();
		username.sendKeys(usrName);
		password.clear();
		password.sendKeys(pssWord);
		//loginBtn.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
    		js.executeScript("arguments[0].click();", loginBtn);
		
		return new HomePage(); // Because HomePage is the landing page of LoginPage 
							  // This method should return an object of HomePage Class
		
	}

} // Class -->> LoginPage


