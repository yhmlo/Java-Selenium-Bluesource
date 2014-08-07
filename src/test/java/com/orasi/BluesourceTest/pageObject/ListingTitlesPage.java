package com.orasi.BluesourceTest.pageObject;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orasi.BluesourceTest.interfaces.Button;
import com.orasi.BluesourceTest.interfaces.Label;
import com.orasi.BluesourceTest.interfaces.Link;
import com.orasi.BluesourceTest.interfaces.Listbox;
import com.orasi.BluesourceTest.interfaces.Textbox;
import com.orasi.BluesourceTest.interfaces.impl.internal.ElementFactory;

public class ListingTitlesPage {
	
	private static WebDriver driver;
	
	//All the page elements
	@FindBy(linkText = "New Title")
	static
	private Link lnkNewTitle;
	
	@FindBy(xpath = "//h1[text() = 'Listing titles']")
	private Label lblTitle;

	@FindBy(css = ".alert-success.alert-dismissable")
	private Label lblSuccessMsg;
	
	//Constructor
	public ListingTitlesPage(WebDriver driver){
		ListingTitlesPage.driver = driver;
		ElementFactory.initElements(driver, this); 
	}
	
	private static void listingTitlesPageLoaded(){
		  while (lnkNewTitle==null){
		      initialize(driver);
		     }
	}
	
	private static ListingTitlesPage initialize(WebDriver driver) {
	     return ElementFactory.initElements(driver, ListingTitlesPage.class);         
	 }
	
	//Methods
	public void ClickNewTitle(){
		lnkNewTitle.click();
	}
	
	public boolean isTitleHeaderDisplayed(){
		return lblTitle.isDisplayed();
	}
	
	public boolean isSuccessMsgDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert-success.alert-dismissable")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success.alert-dismissable")));
		return lblSuccessMsg.isDisplayed();
	}
	
	public boolean SearchTableByTitle(String title){
		
		//Get all the rows in the table by CSS
		List<WebElement> elementList = driver.findElements(By.cssSelector("td"));
		for(WebElement element:elementList){
			//if it matches the title, then return true
			if(element.getText().equals(title)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean DeleteTitle(String title){
		//Get all the rows in the table by CSS
		List<WebElement> elementList = driver.findElements(By.cssSelector("td"));
		for(WebElement element:elementList){
			
			//if it matches the title, then click on the trash element
			if(element.getText().equals(title)){
		
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
