package com.orasi.BluesourceTest.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orasi.BluesourceTest.interfaces.Button;
import com.orasi.BluesourceTest.interfaces.Listbox;
import com.orasi.BluesourceTest.interfaces.Textbox;
import com.orasi.BluesourceTest.interfaces.impl.internal.ElementFactory;
public class NewDeptPage {

	static WebDriver driver;

	//All the page elements
	@FindBy(id = "department_name")
	private static Textbox txtDept;
	
	@FindBy(id = "department_department_id")
	private Textbox txtParentDept;
	
	@FindBy(name = "commit")
	private Button btnCreateDept;
	

	//Constructor
	public NewDeptPage(WebDriver driver){
		NewDeptPage.driver = driver;
		ElementFactory.initElements(driver, this); 
	}
	
	private static void newDeptPageLoaded(){
		  while (txtDept==null){
		      initialize(driver);
		     }
	}
	
	private static NewDeptPage initialize(WebDriver driver) {
	     return ElementFactory.initElements(driver, NewDeptPage.class);         
	 }
	
	//method to create a new title
	public void CreateNewDept(String dept){
		txtDept.safeSet(dept);
		btnCreateDept.click();
	}
}
