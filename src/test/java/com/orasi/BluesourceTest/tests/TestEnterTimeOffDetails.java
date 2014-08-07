package com.orasi.BluesourceTest.tests;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.orasi.BluesourceTest.dataObject.TestAddNewEmployeeData;
import com.orasi.BluesourceTest.dataObject.TestAddNewTitleData;
import com.orasi.BluesourceTest.dataObject.TestEnterTimeOffDetailsData;
import com.orasi.BluesourceTest.pageObject.EmployeeSummaryPage;
import com.orasi.BluesourceTest.pageObject.EmployeesPage;
import com.orasi.BluesourceTest.pageObject.LoginPage;
import com.orasi.BluesourceTest.pageObject.TimeOffDetailsPage;
import com.orasi.BluesourceTest.pageObject.TopNavigationBar;

public class TestEnterTimeOffDetails extends BaseTest {
	
	
	@Test(dataProvider = "createTimeOffData", dataProviderClass = TestEnterTimeOffDetailsData.class)
	public void testEnterTimeOffDetails(TestEnterTimeOffDetailsData testData){
		
		  //Login
		  LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		  loginPage.login(testData.getLoginUsername(), testData.getLoginPassword());

		  //Verify user is logged in
		  TopNavigationBar topNavigationBar = PageFactory.initElements(driver, TopNavigationBar.class);
		  Assert.assertTrue(topNavigationBar.isLoggedIn());
		  Reporter.log("User was logged in successfully");
		  
		  //Select the first employee for that manager
		  EmployeesPage employeesPage = PageFactory.initElements(driver, EmployeesPage.class);
		  employeesPage.selectFirstEmployee();
		  Reporter.log("Selected the first employee");
		  
		  //Click the manage button
		  EmployeeSummaryPage employeeSummaryPage = PageFactory.initElements(driver, EmployeeSummaryPage.class);
		  employeeSummaryPage.ClickManageTimeOff();
		  
		  //First delete all current time off requests so the new time off request doesn't conflict with any 
		  //existing
		  TimeOffDetailsPage timeOffDetailsPage = PageFactory.initElements(driver, TimeOffDetailsPage.class);
		  timeOffDetailsPage.DeleteAllTimeOff();
		  
		  //Enter the time off details
		  timeOffDetailsPage.enterTimeOff(testData.getDateRequested(), testData.getStartDate(), testData.getEndDate(), 
				  							testData.getVacationType(), testData.getOtherReason(), testData.getHalfDay());
		  Reporter.log("Time off was entered");
		  
		  //Verify a success message displays
		  Assert.assertTrue(timeOffDetailsPage.isSuccessMsgDisplayed());
		  assert timeOffDetailsPage.getSuccessMsgText().contains("Time off successfully saved");
		  Reporter.log("Time off was saved successfully");
		  
		  //Clean up - delete all the requests
		  timeOffDetailsPage.DeleteAllTimeOff();
		  
		  //Verify a success message displays
		  Assert.assertTrue(timeOffDetailsPage.isSuccessMsgDisplayed());
		  Reporter.log("Time off requests were deleted successfully");
		  
		  //logout
		  topNavigationBar.logout();
	
	}
}
