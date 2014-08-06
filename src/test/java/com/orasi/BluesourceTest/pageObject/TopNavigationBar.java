package com.orasi.BluesourceTest.pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orasi.BluesourceTest.interfaces.impl.internal.ElementFactory;
import com.orasi.BluesourceTest.interfaces.Button;
import com.orasi.BluesourceTest.interfaces.Link;
import com.orasi.BluesourceTest.interfaces.Listbox;
import com.orasi.BluesourceTest.interfaces.Textbox;
import com.orasi.BluesourceTest.interfaces.impl.internal.ElementFactory;

public class TopNavigationBar {
	private static WebDriver driver;
	
	//All the page elements:
	@FindBy(linkText = "Logout")
	private static Link lnkLogout;
	
	@FindBy(xpath = "//a[text() = 'Admin ']")
	private Link lnkAdminDrop;
	
	@FindBy(css = "a[href = '/admin/departments']")
	private Link lnkDept;
	
	@FindBy(css = "a[href = '/admin/titles']")
	private Link lnkTitle;
	

	//Constructor
	public TopNavigationBar(WebDriver driver){
		TopNavigationBar.driver = driver;
		ElementFactory.initElements(driver, this); 
	}
	
	private static void topNavigationBarLoaded(){
		  while (lnkLogout==null){
		      initialize(driver);
		     }
	}
	
	private static TopNavigationBar initialize(WebDriver driver) {
	     return ElementFactory.initElements(driver, TopNavigationBar.class);         
	 }
	
	//methods
	public void clickAdminLink(){
		lnkAdminDrop.click();
	}
	public void clickDepartmentsLink(){
		lnkDept.click();
	}
	public void clickTitlesLink(){
		lnkTitle.click();
	}
	
	//Verify logout link is displayed
	public boolean isLoggedIn(){
		return lnkLogout.isDisplayed();
	}
	
	//Click logout
	public void logout(){
		lnkLogout.click();
	}
}
