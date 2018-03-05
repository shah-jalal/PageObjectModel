package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String config_properties_path = "/src/main/java/com/crm"
			+ "/qa/config/config.properties";

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ config_properties_path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			//Log.info("browser name: " + prop.get("browser")); //Need to implement this option with Log4j
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome_path"));
			driver = new ChromeDriver();
			//Log.info("launched: " + prop.get("browser")); //Need to implement this option with Log4j
			
		} else if (browserName.equalsIgnoreCase("FF")) {
			//Log.info("browser name: " + prop.get("browser"));
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefox_path"));
			driver = new FirefoxDriver();
			//Log.info("launched: " + prop.get("browser"); //Need to implement this option with Log4j
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListenerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		// Now assign it to my original WebDriver instance
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		// Log.info("app url is: " + prop.getProperty("url")); //Need to implement this option with Log4j

	}
} //Class -->> TestBase
