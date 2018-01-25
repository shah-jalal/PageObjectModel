package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() {
		super(); // super() -->> will invoke the super/parent/base class constructor
				 // to avoid NullPointerException
	}
	
	@BeforeMethod
	public void setUp() {
		initialization(); //It will initialize my WebDriver instance depending 
						  //which which browser object we want to use
		loginPage = new LoginPage(); //The methods in LoginPage are non static to access those we need instantiate an 
									// object of that class
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle(); 
		Assert.assertEquals(title, "#1 Free CRM for Any Business: Online Customer Relationship Software", 
				"Home page title not matched");
		System.out.println(title);
	}
	
	@Test(priority=2) 
	public void crmLogoImageTest() {
		boolean flag = loginPage.validateCRMlogo();
		Assert.assertTrue(flag);
		System.out.println(flag);
	}
	
	@Test(priority=3)
	public void loginTest() {
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
}
