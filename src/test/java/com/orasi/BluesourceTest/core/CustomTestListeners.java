package com.orasi.BluesourceTest.core;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.orasi.BluesourceTest.core.Screenshots;

public class CustomTestListeners extends TestListenerAdapter{

	//overrides the onTestFailure method and takes a screenshot and links it to the test results report
	@Override
	public void onTestFailure(ITestResult tr) {
		Screenshots screenshots = new Screenshots(tr.getName());
		screenshots.takeScreenshot();
	}
	
}
