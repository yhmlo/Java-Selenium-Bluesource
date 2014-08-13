package com.orasi.BluesourceTest.core;
import com.orasi.BluesourceTest.core.CommonUtils;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class CustomListeners extends TestListenerAdapter {
	
	CommonUtils commonUtils = new CommonUtils();
	
	@Override
	public void onTestFailure(ITestResult tr) {
		String methodName = tr.getName();
		commonUtils.TakeScreenshot(methodName);
	}

}
