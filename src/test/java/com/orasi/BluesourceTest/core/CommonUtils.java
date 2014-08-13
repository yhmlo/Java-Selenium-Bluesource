package com.orasi.BluesourceTest.core;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.Reporter;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;

public class CommonUtils {

	public void TakeScreenshot(String methodName){
		try {
			//get current date time with Date() to create unique file name  
			Calendar calendar = Calendar.getInstance();
		    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		    String failFileName = null;
		    failFileName = "failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png";
		    
		    //pull in the toolkit
		    Toolkit toolkit = Toolkit.getDefaultToolkit();
		    
		    //Get the current screen size
		  	Dimension scrnsize = toolkit.getScreenSize();	
		    
		    //Capture the screen shot of the area of the screen defined by the rectangle
		  	Robot robot = new Robot();
		  	
		    //BufferedImage bi=robot.createScreenCapture(new Rectangle(1280,1024));
		  	BufferedImage bi=robot.createScreenCapture(new Rectangle(scrnsize));
		  	ImageIO.write(bi, "png", new File(failFileName));
			
			//Place the reference in TestNG web report 
			Reporter.log("<br /> <br /> Failed step screenshot <br /> <br /> ");
			Reporter.log("<img src=\"file:///" + failFileName + "\" alt=\"\"/><br />");
		
		} 
		catch (AWTException e) {
		e.printStackTrace();
		} 
		catch (IOException e) {
		e.printStackTrace();
		} catch (NoSuchElementException e){
			
		}
	}
	
}
