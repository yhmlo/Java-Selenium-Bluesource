package com.orasi.BluesourceTest.dataObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;

public class TestAddNewTitleData {

	private String loginUsername;
	private String loginPassword;
	private String newTitle;
	
    public String getloginUsername(){
    	return loginUsername;
    }
    public void setloginUsername(String loginUsername){
    	this.loginUsername = loginUsername;
    }
    public String getloginPassword(){
    	return loginPassword;
    } 
    public void setloginPassword(String loginPassword){
    	this.loginPassword = loginPassword;
    }
    public String getNewTitle(){
    	return newTitle;
    } 
    public void setNewTitle(String newTitle){
    	this.newTitle = newTitle;
    }
    
    public static int createRandomNum(){
    	Random rand = new Random();
	    int randomNum = rand.nextInt((800) + 1) + 100;
	    return randomNum;
	    
    }

    //Get data from a csv file - first row is a header
    @DataProvider(name = "createNewTitleData")
    public static Object [][] createNewTitleData() throws Exception{
    	
		//open the CSV file
		CSVReader csvReader;
		String path = "C:\\Maven\\BluesourceSeleniumTests\\src\\resources\\TestAddNewTitle.csv";
		try {
			csvReader = new CSVReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			
			Reporter.log("CSV file was not found in path: " + path + "<br>");
			throw (e);
		}
		
		//get the contents into a list
		List<String[]>dataList = csvReader.readAll();
		
		//create a data object for the data provider the size of the contents of the csv file minus the heading
		Object[][] data = new Object[dataList.size()-1][1];
		
		//create a list to hold all the data objects
		List<TestAddNewTitleData> addTitleList = new ArrayList<TestAddNewTitleData>();
		
		//set each row of data into the test data object which is held in the employee list
		for(String[] strArray:dataList) {
	    	TestAddNewTitleData titleData = new TestAddNewTitleData();
	    	titleData.setloginUsername(strArray[0].trim());
	    	titleData.setloginPassword(strArray[1].trim());
	    	titleData.setNewTitle("NewTitle" + createRandomNum());
	    	addTitleList.add(titleData);
		}
		
		//add the employee list to the data object to be returned from the data provider
		//since the first row is a header, start at the second row
		for(int i=1; i<addTitleList.size(); i++){
			for(int j=0; j<data[i-1].length; j++){
				data[i-1][j]=addTitleList.get(i);
			}
		}


		csvReader.close();
		return data;
    }

}
