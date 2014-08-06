package com.orasi.BluesourceTest.pageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orasi.BluesourceTest.interfaces.Button;
import com.orasi.BluesourceTest.interfaces.Textbox;
import com.orasi.BluesourceTest.interfaces.impl.internal.ElementFactory;



public class LoginPage {
	static WebDriver driver;
	
	//all the page elements
	@FindBy(id = "employee_username")
	private Textbox txtUsername;
	
	@FindBy(id = "employee_password")
	private  Textbox txtPassword;
	
	@FindBy(name = "commit")
	private static  Button btnLogin;
	
	
	//Constructor
	public LoginPage(WebDriver driver){
		LoginPage.driver = driver;
		ElementFactory.initElements(driver, this); 
	}
	
	private static void loginPageLoaded(){
		  while (btnLogin==null){
		      initialize(driver);
		     }
	}
	
	private static LoginPage initialize(WebDriver driver) {
	     return ElementFactory.initElements(driver, LoginPage.class);         
	 }
	
	//Methods
	
	public void login(String username, String password) {
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(txtUsername));

		txtUsername.safeSet(username);
		txtPassword.safeSet(password);
		btnLogin.click();
	}
	

	  

}
