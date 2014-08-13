package com.orasi.BluesourceTest.tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.orasi.BluesourceTest.core.CommonUtils;



public class BaseTest extends CommonUtils{
	

	public WebDriver driver;
	public static StringBuffer verificationErrors = new StringBuffer();

	  @BeforeTest
	  @Parameters("browser")
	  public void launchBrowser(String browser) throws MalformedURLException {
		  	
		  	//String browser = "Chrome";
	
			
			if (browser.equalsIgnoreCase("Chrome") ){
				
				//URL seleniumRemoteChrome = new URL("http", "10.238.242.209", 5555, "/wd/hub");
				//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				//driver = new RemoteWebDriver(seleniumRemoteChrome, capabilities);
				
				File file = new File("C:/Selenium/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				driver = new ChromeDriver();
				
				//capabilities.setJavascriptEnabled(true);
			} else if (browser.equalsIgnoreCase("Firefox")) {
				
				driver = new FirefoxDriver();
				//URL seleniumRemoteFirefox = new URL("http", "10.238.242.249", 5555, "/wd/hub");
				//DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				//driver = new RemoteWebDriver(seleniumRemoteFirefox, capabilities);
				//capabilities.setJavascriptEnabled(true);
			} else if (browser.equalsIgnoreCase("IE")){
				
				File file = new File("C:/Selenium/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver();
				
				
				//URL seleniumRemoteIE = new URL ("http", "10.238.242.51", 5555, "/wd/hub");
				//DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				//driver = new RemoteWebDriver(seleniumRemoteIE, capabilities);
				//capabilities.setJavascriptEnabled(true);
			}else if (browser.equalsIgnoreCase("htmlUnitDriver")) {
				
				driver = new HtmlUnitDriver();
				
			}
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); 
		  
			//launch the browser
			driver.get("http://bluesourcestaging.herokuapp.com/login");
			
	  }

	  
	  @AfterTest
	  public void cleanUp() {
		  //driver.close();
		  driver.quit();
		  
	  }

}

