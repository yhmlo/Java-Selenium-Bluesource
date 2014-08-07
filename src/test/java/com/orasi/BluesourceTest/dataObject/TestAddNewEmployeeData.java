package com.orasi.BluesourceTest.dataObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;





import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import com.google.common.io.Resources;

import au.com.bytecode.opencsv.CSVReader;

public class TestAddNewEmployeeData  {
	
	private String loginUsername;
	private String loginPassword;
    private String username;
	private String firstName;
    private String lastName;
    private String title;
    private String role;
    private String manager;
    private String status;
    private String location;
    private String startDate;
    private String cellPhone;
    private String officePhone;
    private String email;
    private String dept;
    
	//Getters & Setters
	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
    public String getUsername(){
    	return username;
    }
    public void setUsername(String username){
    	this.username = username;
    }
 
    public String getFirstName(){
    	return firstName;
    }
    public void setFirstName(String firstName){
    	this.firstName = firstName;
    }
    public String getLastName(){
    	return lastName;
    }
    public void setLastName(String lastName){
    	this.lastName = lastName;
    }
    public String getTitle(){
    	return title;
    }
    public void setTitle(String title){
    	this.title = title;
    }
    public String getRole(){
    	return role;
    }
    public void setRole(String role){
    	this.role = role;
    }
    public String getManager(){
    	return manager;
    }
    public void setManager(String manager){
    	this.manager = manager;
    }
    public String getStatus(){
    	return status;
    }
    public void setStatus(String status){
    	this.status = status;
    }
    public String getLocation(){
    	return location;
    }
    public void setLocation(String location){
    	this.location = location;
    }
    public String getStartDate(){
    	return startDate;
    }
    public void setStartDate(String startDate){
    	this.startDate = startDate;
    }
    public String getCellPhone(){
    	return cellPhone;
    }
    public void setCellPhone(String cellPhone){
    	this.cellPhone = cellPhone;
    }
    public String getOfficePhone(){
    	return officePhone;
    }
    public void setOfficePhone(String officePhone){
    	this.officePhone = officePhone;
    }
    public String getEmail(){
    	return email;
    }
    public void setEmail(String email){
    	this.email = email;
    }
    public String getDept(){
    	return dept;
    }
    public void setDept(String dept){
    	this.dept = dept;
    }
    //methods
    public static int createRandomNum(){
    	Random rand = new Random();
	    int randomNum = rand.nextInt((800) + 1) + 100;
	    return randomNum;    
    }
    
    //data providers
    //Get data from a csv file; first row is a header
	@DataProvider(name = "addEmpData")
	public static Object[][] createEmployeeData() throws Exception{
		//open the CSV file
		CSVReader csvReader;
		String path = "C:\\Maven\\BluesourceSeleniumTests\\src\\resources\\TestAddNewEmployee.csv";
		try {
			csvReader = new CSVReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			
			Reporter.log("CSV file was not found in path: " + path + "<br>");
			throw (e);
		}
		
		//get the contents into a list
		List<String[]>dataList = csvReader.readAll();
		
		//create a data object for the data provider the size of the contents of the csv file minus the first row which is a header
		Object[][] data = new Object[dataList.size()-1][1];
		
		//create a list to hold all the data objects
		List<TestAddNewEmployeeData> addEmpList = new ArrayList<TestAddNewEmployeeData>();
		
		//to get the current date and format it
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
		
		//set each row of data into the test data object which is held in the employee list
		for(String[] strArray:dataList) {
			TestAddNewEmployeeData testData = new TestAddNewEmployeeData();
			testData.setLoginUsername(strArray[0].trim());
			testData.setLoginPassword(strArray[1].trim());
		    testData.setFirstName("Firstname" + createRandomNum());
		    testData.setLastName("Lastname" + createRandomNum());
			testData.setUsername(testData.getFirstName() + testData.getLastName());
			testData.setTitle(strArray[5].trim());
			testData.setRole(strArray[6].trim());
			testData.setManager(strArray[7].trim());
			testData.setStatus(strArray[8].trim());
			testData.setLocation(strArray[9].trim());
			testData.setStartDate(ft.format(date));
			testData.setCellPhone(strArray[11].trim());
			testData.setOfficePhone(strArray[12].trim());
			testData.setEmail("test" + createRandomNum() + "@test.com");
			testData.setDept(strArray[14].trim());
			addEmpList.add(testData);
		}

		//add the employee list to the data object to be returned from the data provider
		//since the first row is a header, start at the second row
		for(int i=1; i<addEmpList.size(); i++){
			for(int j=0; j<data[i-1].length; j++){
				data[i-1][j]=addEmpList.get(i);
			}
		}
		
		csvReader.close();
		return data;
	}

}
