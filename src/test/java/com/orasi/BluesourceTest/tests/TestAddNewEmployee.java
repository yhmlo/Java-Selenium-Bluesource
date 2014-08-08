package com.orasi.BluesourceTest.tests;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;

import com.orasi.BluesourceTest.pageObject.AddNewEmployeePage;
import com.orasi.BluesourceTest.pageObject.EmployeesPage;
import com.orasi.BluesourceTest.pageObject.LoginPage;
import com.orasi.BluesourceTest.pageObject.TopNavigationBar;
import com.orasi.BluesourceTest.dataObject.TestAddNewEmployeeData;



public class TestAddNewEmployee extends BaseTest {
	
	  //Add a new employee and verify a success message	
	  @Test(dataProvider = "addEmpData", dataProviderClass = TestAddNewEmployeeData.class)
	  public void testAddEmployee(TestAddNewEmployeeData testData) throws Exception {
		  

		  //Login
		  LoginPage loginPage = new LoginPage(driver);
		  loginPage.login(testData.getLoginUsername(), testData.getLoginPassword());

		  //Verify user is logged in
		  TopNavigationBar topNavigationBar = PageFactory.initElements(driver, TopNavigationBar.class);
		  Assert.assertTrue(topNavigationBar.isLoggedIn());
		  Reporter.log("User was logged in successfully");
		  
		  //click add
		  EmployeesPage employeesPage = PageFactory.initElements(driver, EmployeesPage.class);
		  employeesPage.clickAddNewEmployee();
		  Reporter.log("Clicked add");
		  
		  //Fill out the new employee details and submit
		  AddNewEmployeePage addNewEmployeePage = PageFactory.initElements(driver, AddNewEmployeePage.class);
		  addNewEmployeePage.addEmployee(testData.getUsername(), testData.getFirstName(), testData.getLastName(), testData.getTitle(),
				  						testData.getRole(), testData.getManager(), testData.getStatus(), testData.getLocation(), 
				  						testData.getStartDate(), testData.getCellPhone(), testData.getOfficePhone(), testData.getEmail(),
				  						testData.getDept());		

		  //verify success message
		  assert employeesPage.getSuccessMsgText().contains("Employee added successfully");
		  Reporter.log("New employee was added successfully");
		  
		  //search for the employee and verify it was found
		  Assert.assertTrue(employeesPage.searchTableByFirstAndLastName(testData.getFirstName(), testData.getLastName() ));
		  Reporter.log("New user was found in list of employees");
		  
		  //logout
		  topNavigationBar.logout();
	  }
  



}
