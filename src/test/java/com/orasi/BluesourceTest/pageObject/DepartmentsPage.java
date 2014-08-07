package com.orasi.BluesourceTest.pageObject;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orasi.BluesourceTest.interfaces.Label;
import com.orasi.BluesourceTest.interfaces.Link;
import com.orasi.BluesourceTest.interfaces.impl.internal.ElementFactory;
public class DepartmentsPage {
	
	static WebDriver driver;
	
	//All the page elements
	@FindBy(linkText = "Add Department")
	static
	private Link lnkAddDept;
	
	@FindBy(xpath = "//h1[text() = 'Departments']")
	private Label lblTitle;

	@FindBy(css = ".alert-success.alert-dismissable")
	private Label lblSuccessMsg;
	
	//Constructor
	public DepartmentsPage(WebDriver driver){
		DepartmentsPage.driver = driver;
		ElementFactory.initElements(driver, this); 
	}
	
	//Methods
	private static void departmentsPageLoaded(){
		  while (lnkAddDept==null){
		      initialize(driver);
		     }
	}
	
	private static DepartmentsPage initialize(WebDriver driver) {
	     return ElementFactory.initElements(driver, DepartmentsPage.class);         
	 }
	
	//click add dept link
	public void ClickAddDeptLink(){
		lnkAddDept.click();
	}
	
	public boolean isTitleHeaderDisplayed(){
		return lblTitle.isDisplayed();
	}
	
	//return if the success message is displayed
	public boolean IsSuccessMsgDisplayed(){
		return lblSuccessMsg.isDisplayed();
	}
	
	//search page for a dept, return if displayed
	public boolean SearchTableByDept(String dept){
		//Get all the rows in the table by CSS
		List<WebElement> elementList = driver.findElements(By.cssSelector(".list-group-item"));
		for(WebElement element:elementList){
			//if it matches the title, then return true
			if(element.getText().contains(dept)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean DeleteDept(String dept){
		//Get all the rows in the table by CSS
		List<WebElement> elementList = driver.findElements(By.cssSelector(".list-group-item"));
		for(WebElement element:elementList){
			
			//if it matches the title, then click on the trash element
			if(element.getText().equals(dept)){
		
				//click on the trash element
				element.findElement(By.cssSelector("a[data-method = 'delete']")).click();
				
				//accept the alert that pops up
				Alert alert = driver.switchTo().alert();
				alert.accept();
				return true;
			}
		}
		return false;
	}
	
	

}
