package com.orasi.BluesourceTest.dataObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;

public class TestAddNewDeptData {
	
	private String loginUsername;
	private String loginPassword;
	private String newDept;
	
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
    public String getNewDept(){
    	return newDept;
    } 
    public void setNewDept(String newDept){
    	this.newDept = newDept;
    }
    
    public static int createRandomNum(){
    	Random rand = new Random();
	    int randomNum = rand.nextInt((800) + 1) + 100;
	    return randomNum;
	    
    }

    //Get data from a csv file - first row is a header
    @DataProvider(name = "createNewDeptData")
    public static Object [][] createNewDeptData() throws Exception{
    	
		//open the CSV file
		CSVReader csvReader;
		String path = "src/test/resources/TestAddNewDept.csv";
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
		List<TestAddNewDeptData> addDeptList = new ArrayList<TestAddNewDeptData>();
		
		//set each row of data into the test data object which is held in the employee list
		for(String[] strArray:dataList) {
			TestAddNewDeptData deptData = new TestAddNewDeptData();
			deptData.setloginUsername(strArray[0].trim());
			deptData.setloginPassword(strArray[1].trim());
			deptData.setNewDept("NewDept" + createRandomNum());
	    	addDeptList.add(deptData);
		}
		
		//add the employee list to the data object to be returned from the data provider
		//since the first row is a header, start at the second row
		for(int i=1; i<addDeptList.size(); i++){
			for(int j=0; j<data[i-1].length; j++){
				data[i-1][j]=addDeptList.get(i);
			}
		}


		csvReader.close();
		return data;
    }

}
