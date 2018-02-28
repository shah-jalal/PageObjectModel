package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void Setup() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts Label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest(){
		contactsPage.selectContactsByName("David Burton");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest(){
		contactsPage.selectContactsByName("David Burton");
		contactsPage.selectContactsByName("David Davis");

	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company) {
		homePage.clickOnNewContact();
		contactsPage.createNewContacts(title, firstName, lastName, company);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
