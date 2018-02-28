package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static String frameName = "mainpanel";
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	
	public static String TESTDATA_SHEET_PATH = "/src/main/java/com/crm/qa/testdata/FreeCrmTestData.xlsx";
	
	//Note -->> System.getProperty("user.dir") fetches the directory or path of the workspace for the current project
	//"user.dir" --> User working directory (path of the workspace for the current project)

	static Workbook book;
	static Sheet sheet;

	public void switchToFrame() {
		driver.switchTo().frame(frameName);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(System.getProperty("user.dir") + TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		int rowNum =  sheet.getLastRowNum(); // Get the total number of rows
		int colNum =  sheet.getRow(0).getLastCellNum(); // Get the total number of columns
		for (int i = 0; i < rowNum; i++) { // Looping through the row
			for (int j = 0; j < colNum; j++) { // Looping through the column
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	//Generating Date-format for file name (TimeZone -->> EST - Eastern; PST - Pacific; CST - Central)
	private final static String getDateTime() {
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
	    df.setTimeZone(TimeZone.getTimeZone("EST"));
	    return df.format(new Date());
	}
	
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");

		//FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + getDateTime() + ".png"));
	}

}// Class -->> TestUtil
