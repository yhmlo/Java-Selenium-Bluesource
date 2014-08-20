package com.orasi.BluesourceTest.tests;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.orasi.BluesourceTest.dataObject.TestAddNewDeptData;
import com.orasi.BluesourceTest.pageObject.DepartmentsPage;
import com.orasi.BluesourceTest.pageObject.LoginPage;
import com.orasi.BluesourceTest.pageObject.NewDeptPage;
import com.orasi.BluesourceTest.pageObject.TopNavigationBar;


public class TestAddNewDept extends BaseTest {
	
	//Create a new title
	@Test(dataProvider = "createNewDeptData", dataProviderClass = TestAddNewDeptData.class)
	public void testCreateNewDept(TestAddNewDeptData testData){
	
		//Login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(testData.getloginUsername(), testData.getloginPassword());
	  
		//Verify user is logged in
		TopNavigationBar topNavigationBar = new TopNavigationBar(driver);
		Assert.assertTrue(topNavigationBar.isLoggedIn());
		Reporter.log("User was logged in successfully");
		
		//Navigate to the dept page
		topNavigationBar.clickAdminLink();
		topNavigationBar.clickDepartmentsLink();
		
		//Verify navigated to the dept page
		DepartmentsPage deptPage = new DepartmentsPage(driver);
		Assert.assertTrue(deptPage.isTitleHeaderDisplayed());
		Reporter.log("Navigated to the department page");
		
		//Add a new dept
		deptPage.ClickAddDeptLink();
		NewDeptPage newDeptPage = new NewDeptPage(driver);
		newDeptPage.CreateNewDept(testData.getNewDept());
		
		//Verify the dept is added
		Assert.assertTrue(deptPage.IsSuccessMsgDisplayed());
		Reporter.log("New Dept was created: " + testData.getNewDept());
		
		//Verify the title is displayed on the title results table
		Assert.assertTrue(deptPage.SearchTableByDept(testData.getNewDept()));
		Reporter.log("New dept was found in table of titles<br>");
		
		//Delete the new title
		deptPage.DeleteDept(testData.getNewDept());
		
		//Verify the title is deleted
		DepartmentsPage refreshedPage = new DepartmentsPage(driver);
		Assert.assertTrue(refreshedPage.IsSuccessMsgDisplayed());
		Reporter.log("New dept was deleted successfully<br>");
		
		//logout
		topNavigationBar.logout();
		
	}

}
