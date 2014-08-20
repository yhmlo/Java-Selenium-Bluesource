package com.orasi.BluesourceTest.pageObject;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orasi.BluesourceTest.interfaces.Button;
import com.orasi.BluesourceTest.interfaces.Label;
import com.orasi.BluesourceTest.interfaces.Listbox;
import com.orasi.BluesourceTest.interfaces.Textbox;
import com.orasi.BluesourceTest.interfaces.impl.internal.ElementFactory;

public class TimeOffDetailsPage {
	
	private static WebDriver driver;
	
	//All the page elements
	@FindBy(id = "new_vacation_date_requested")
	private static Textbox txtDateRequested;
	
	@FindBy(id = "new_vacation_start_date")
	private Textbox txtStartDate;
	
	@FindBy(id = "new_vacation_end_date")
	private Textbox txtEndDate;
	
	@FindBy(className = "business-days")
	private Label lblTotalDays;
	
	@FindBy(id = "new_vacation_vacation_type")
	private Listbox lstVacationType;
	
	@FindBy(css = "input[class = 'half-day']")
	private Button btnHalfDay;
	
	@FindBy(css = "input[value = 'Save Time Off']")
	private Button btnSave;
	
	@FindBy(css = ".alert-success.alert-dismissable")
	private Label lblSuccessMsg;
	
	@FindBy(name = "new[vacation][reason]")
	private Textbox txtReason;
	

	//Constructor
	public TimeOffDetailsPage(WebDriver driver){
		TimeOffDetailsPage.driver = driver;
		ElementFactory.initElements(driver, this); 
	}
	
	private static void timeOffDetailsPageLoaded(){
		  while (txtDateRequested==null){
		      initialize(driver);
		     }
	}
	
	private static TimeOffDetailsPage initialize(WebDriver driver) {
	     return ElementFactory.initElements(driver, TimeOffDetailsPage.class);         
	 }
	
	//Methods
	public boolean isSuccessMsgDisplayed(){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert-success.alert-dismissable")));
		return lblSuccessMsg.isDisplayed();
	}
	
	public String getSuccessMsgText(){
		return lblSuccessMsg.getText();
	}
	
	public void hoverOverElement(WebElement element){
		Actions builder = new Actions(driver);
		Actions hoverOverRegistrar = builder.moveToElement(element);
		hoverOverRegistrar.perform();
	}
	
	//Enter time off
	public void enterTimeOff(String dateRequested, String startDate, String endDate, String vacationType,
								String otherReason, String halfDay ) {
		//Enter the data
		txtDateRequested.safeSet(dateRequested);
		txtStartDate.safeSet(startDate);
		txtEndDate.safeSet(endDate);
		lstVacationType.select(vacationType);
		
		//If the vacation type is 'Other', then need to fill out the reason field
		if (vacationType.equalsIgnoreCase("Other")) {
			//first you must hover over the vacation type field after selecting other
			hoverOverElement(lstVacationType);
			txtReason.safeSet(otherReason);
		}

		//If its a half day
		if (halfDay.equalsIgnoreCase("TRUE")){
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(btnHalfDay));
			btnHalfDay.click();
		}
		

		btnSave.click();

	}
	
	//Delete a specific time off entry
	public void DeleteTimeOff(String startDate, String endDate){
		
	}
	
	//Clean up - delete all the time off requests
	public void DeleteAllTimeOff(){
		
		List<WebElement> deleteIconsList = driver.findElements(By.cssSelector("a[data-method = 'delete']"));
		if (deleteIconsList.size() > 0){
			for(WebElement element:deleteIconsList){
				element.click();
				
				//accept the alert that pops up
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
		}

	}
	
}
