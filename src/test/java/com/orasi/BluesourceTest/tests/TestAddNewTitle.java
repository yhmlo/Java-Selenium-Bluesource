package com.orasi.BluesourceTest.tests;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.orasi.BluesourceTest.pageObject.ListingTitlesPage;
import com.orasi.BluesourceTest.pageObject.LoginPage;
import com.orasi.BluesourceTest.pageObject.NewTitlePage;
import com.orasi.BluesourceTest.pageObject.TopNavigationBar;
import com.orasi.BluesourceTest.dataObject.TestAddNewTitleData;


public class TestAddNewTitle extends BaseTest{
	

	//Create a new title
	@Test(dataProvider = "createNewTitleData", dataProviderClass = TestAddNewTitleData.class)
	public void testCreateNewTitle(TestAddNewTitleData testData){
		
		//Login
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login(testData.getloginUsername(), testData.getloginPassword());
	  
		//Verify user is logged in
		TopNavigationBar topNavigationBar = PageFactory.initElements(driver, TopNavigationBar.class);
		Assert.assertTrue(topNavigationBar.isLoggedIn());
		Reporter.log("User was logged in successfully");
		
		//Navigate to the title page
		topNavigationBar.clickAdminLink();
		topNavigationBar.clickTitlesLink();
		
		//Verify navigated to the title page
		ListingTitlesPage listingTitlesPage = PageFactory.initElements(driver, ListingTitlesPage.class);
		Assert.assertTrue(listingTitlesPage.isTitleHeaderDisplayed());
		Reporter.log("Navigated to the listing titles page<br>");

		//Click new title
		listingTitlesPage.ClickNewTitle();
		Reporter.log("Navigated to the new title page<br>");
		
		//Instantiate the New titles page and create a new title
		NewTitlePage newTitlePage = PageFactory.initElements(driver, NewTitlePage.class);
		newTitlePage.CreateNewTitle(testData.getNewTitle());
		
		//Verify the title was created
		Assert.assertTrue(listingTitlesPage.isSuccessMsgDisplayed());
		Reporter.log("New Title was created: " + testData.getNewTitle() + "<br>");
		
		//Verify the title is displayed on the title results table
		Assert.assertTrue(listingTitlesPage.SearchTableByTitle(testData.getNewTitle()));
		Reporter.log("New title was found in table of titles<br>");
		
		//Delete the new title
		listingTitlesPage.DeleteTitle(testData.getNewTitle());
		
		//Verify the title is deleted
		ListingTitlesPage refreshedPage = PageFactory.initElements(driver, ListingTitlesPage.class);
		Assert.assertTrue(refreshedPage.isSuccessMsgDisplayed());
		Reporter.log("New title was deleted successfully<br>");
		
		//logout
		topNavigationBar.logout();
		
	}
	


}
