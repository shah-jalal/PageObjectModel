package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super();
	}
	
	//test cases should be separated -->> independent with each other
	//before each test case -->> launch the browser and login 
	//@test -->> execute
	//after each test cases -->> close the browser
	
	@BeforeMethod
	public void Setup() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home page title not matched");
	}
	
	@Test(priority=2)
	public void verifyCorrectUserNameTest() {
		testUtil.switchToFrame();
		boolean flag = homePage.verifyCorrectUserName();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
