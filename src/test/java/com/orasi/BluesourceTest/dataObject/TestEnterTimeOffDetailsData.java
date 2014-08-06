package com.orasi.BluesourceTest.dataObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;

public class TestEnterTimeOffDetailsData {
	private String loginUsername;
	private String loginPassword;
	private String dateRequested;
	private String startDate;
	private String endDate;
	private String vacationType;
	private String otherReason;
	private String halfDay;


	//Getters & setters
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
	
	public String getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(String dateRequested) {
		this.dateRequested = dateRequested;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getVacationType() {
		return vacationType;
	}
	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}
	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}
	public String getHalfDay() {
		return halfDay;
	}
	public void setHalfDay(String halfDay) {
		this.halfDay = halfDay;
	}



	//Get data from a csv file - first row is a header
    @DataProvider(name = "createTimeOffData")
    public static Object [][] createTimeOffData() throws Exception{
    	
		//to get the current date and format it
		Calendar startDate = Calendar.getInstance();    
		Calendar endDate = Calendar.getInstance(); 
		Calendar requestedDate = Calendar.getInstance(); 
		SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-YYYY");
		
		//open the CSV file
		CSVReader csvReader;
		String path = "C:\\Maven\\BluesourceSeleniumTests\\src\\resources\\TestEnterTimeOffDetails.csv";
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
		List<TestEnterTimeOffDetailsData> timeOffList = new ArrayList<TestEnterTimeOffDetailsData>();
		
		//set each row of data into the test data object which is held in the employee list
		for(String[] strArray:dataList) {
			

			TestEnterTimeOffDetailsData testData = new TestEnterTimeOffDetailsData();
			testData.setLoginUsername(strArray[0].trim());
			testData.setLoginPassword(strArray[1].trim());
			//for the requested date, if its marked skipped, use todays date
			if (strArray[2].equalsIgnoreCase("<SKIP>")) {
				requestedDate.setTime(new Date());
				testData.setDateRequested(ft.format(requestedDate.getTime()));
			}
			else {
				testData.setDateRequested(strArray[2]);
			}

			
			//for the start date, if its marked skipped, pick today 
			if (strArray[3].equalsIgnoreCase("<SKIP>")) {
				startDate.setTime(new Date());
				testData.setStartDate(ft.format(startDate.getTime()));
			}
			else {
				testData.setStartDate(strArray[3]);
			}
			
			//for the end date, if its marked skipped, pick today unless its a weekend
			if (strArray[4].equalsIgnoreCase("<SKIP>")) {
				endDate.setTime(new Date());
				//endDate.add(Calendar.DATE, 1);
				if (endDate.get(Calendar.DAY_OF_WEEK) == 7 || endDate.get(Calendar.DAY_OF_WEEK) == 1){
					endDate.add(Calendar.DATE, 2);
				}
				testData.setEndDate(ft.format(endDate.getTime()));
			}
			else {
				testData.setEndDate(strArray[4]);
			}
			
			//vacation type
			testData.setVacationType(strArray[5]);
			
			//other vacation type reason
			testData.setOtherReason(strArray[6]);
			
			//half day (true or false)
			testData.setHalfDay(strArray[7]);
			timeOffList.add(testData);

		}
		
		//add the employee list to the data object to be returned from the data provider
		//since the first row is a header, start at the second row
		for(int i=1; i<timeOffList.size(); i++){
			for(int j=0; j<data[i-1].length; j++){
				data[i-1][j]=timeOffList.get(i);
			}
		}


		csvReader.close();
		return data;
    }
}
