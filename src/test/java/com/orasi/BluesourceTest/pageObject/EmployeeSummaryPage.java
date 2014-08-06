package com.orasi.BluesourceTest.pageObject;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.orasi.BluesourceTest.interfaces.Button;
import com.orasi.BluesourceTest.interfaces.Link;
import com.orasi.BluesourceTest.interfaces.Listbox;
import com.orasi.BluesourceTest.interfaces.Textbox;
import com.orasi.BluesourceTest.interfaces.impl.internal.ElementFactory;

public class EmployeeSummaryPage {
	static WebDriver driver;
	
	//All the page elements
	@FindBy(xpath = "//*[@id='accordion']/div/div[6]/div[1]/a[2]")
	private Link lnkManageTimeOff;
	
	@FindBy(linkText = "View")
	static
	private Link lnkViewTimeOff;
	
	//Constructor
	public EmployeeSummaryPage(WebDriver driver){
		EmployeeSummaryPage.driver = driver;
		ElementFactory.initElements(driver, this); 
	}
	
	private static void employeeSummaryPageeLoaded(){
		  while (lnkViewTimeOff==null){
		      initialize(driver);
		     }
	}
	
	private static EmployeeSummaryPage initialize(WebDriver driver) {
	     return ElementFactory.initElements(driver, EmployeeSummaryPage.class);         
	 }
	
	//Methods:
	public void ClickManageTimeOff(){
		lnkManageTimeOff.click();
	}
	
	public void ViewManageTimeOff(){
		lnkViewTimeOff.click();
	}
	
	
}
