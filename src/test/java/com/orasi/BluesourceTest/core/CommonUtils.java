package com.orasi.BluesourceTest.core;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CommonUtils {

/*	public File TakeScreenshot(String methodName, WebDriver driver){
	      Calendar calendar = Calendar.getInstance();
	      SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	      String failFileName = null;
          File imageFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          //try {
        	  failFileName = "failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png";
        	  System.out.println(failFileName);
        	  File failFile = new File(failFileName);
        	  try {
				//FileUtils.moveFile(imageFile, failFile);
			} //catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	  Reporter.log("<a href=" + failFile.getAbsolutePath() + "</a>");

        	  Reporter.log("Failure");
        	  //Reporter.log("<a href='" + failFile.getAbsolutePath() + "'>screenshot</a>");
        	  return failFile;
         // } catch (IOException e1) {
           //   e1.printStackTrace();
         // }
		//return null;
*/
	
}
