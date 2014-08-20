package com.orasi.BluesourceTest.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.orasi.BluesourceTest.interfaces.Button;
import com.orasi.BluesourceTest.interfaces.Listbox;
import com.orasi.BluesourceTest.interfaces.Textbox;
import com.orasi.BluesourceTest.interfaces.impl.internal.ElementFactory;




public class AddNewEmployeePage {

	private static WebDriver driver;
	private static StringBuffer verificationErrors = new StringBuffer();

	//All the page elements:
	@FindBy(id = "employee_username")
	static
	private Textbox txtUsername;
	
	@FindBy(id = "employee_first_name")
	private Textbox txtFirstName;
	
	@FindBy(id = "employee_last_name")
	private Textbox txtLastName;
	
	@FindBy(id = "employee_title_id")
	private Listbox lstTitle;
	
	@FindBy(id = "employee_role")
	private Listbox lstRole;
	
	@FindBy(id = "employee_manager_id")
	private Listbox lstManager;
	
	@FindBy(id = "employee_status")
	private Listbox lstStatus;
	
	@FindBy(id = "employee_location")
	private Listbox lstLocation;
	
	@FindBy(id = "employee_start_date")
	private Textbox txtStartDate;
	
	@FindBy(id = "employee_cell_phone")
	private Textbox txtCellPhone;
	
	@FindBy(id = "employee_office_phone")
	private Textbox txtOfficePhone;
	
	@FindBy(id = "employee_email")
	private Textbox txtEmail;
	
	@FindBy(id = "employee_department_id")
	private Listbox lstDept;
	
	@FindBy(name = "commit")
	private Button btnCreateEmp;

	//Constructor
	public AddNewEmployeePage(WebDriver driver){
		AddNewEmployeePage.driver = driver;
		ElementFactory.initElements(driver, this); 
	}
	
	private static void addNewEmployeePageLoaded(){
		  while (txtUsername==null){
		      initialize(driver);
		     }
	}
	
	private static AddNewEmployeePage initialize(WebDriver driver) {
	     return ElementFactory.initElements(driver, AddNewEmployeePage.class);         
	 }

	//adds a new employee on the new employee page
	public void addEmployee(String username, String firstName, String lastName, String title, String role, String manager,
							String status, String location, String startDate, String cellPhone, String officePhone, 
							String email, String dept) throws Exception {
		  

		  //variables	
		  WebDriverWait wait = new WebDriverWait(driver, 60);

		  //wait until page loads
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("employee_username")));

		  //Fill in the details
		  try {
			  txtUsername.safeSet(username);
			  txtUsername.safeSet(username);
			  txtFirstName.safeSet(firstName);
			  txtLastName.safeSet(lastName);
			  lstTitle.select(title);
			  lstRole.select(role);
			  lstManager.select(manager);
			  lstStatus.select(status);
			  lstLocation.select(location);
			  txtStartDate.safeSet(startDate);
			  txtCellPhone.safeSet(cellPhone);
			  txtOfficePhone.safeSet(officePhone);
			  txtEmail.safeSet(email);
			  lstDept.select(dept);
		  
			  //submit
			  btnCreateEmp.click();
			  
		  }catch (Exception e){
			  verificationErrors.append(e.toString());
			  Reporter.log("Element not found on the add employee frame: " + e);
			  throw(e);
		  }
		  
	  }
}
