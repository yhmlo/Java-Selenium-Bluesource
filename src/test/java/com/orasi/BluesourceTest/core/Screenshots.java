package com.orasi.BluesourceTest.core;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;


public class Screenshots extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		takeScreenshot(tr.getName());
	}

	private void takeScreenshot(String testName) {
		try {

			// get current date time with Date() to create unique file name
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat(
					"dd_MM_yyyy_hh_mm_ss");
			String failFileName = null;
			String failFilePath = null;

			// directory location for the screenshots
			String destDir = "test-output/screenshots/";

			// create the fail file name & the path
			failFileName = testName + "_"
					+ formater.format(calendar.getTime()) + ".png";
			failFilePath = destDir + failFileName;

			// pull in the toolkit
			Toolkit toolkit = Toolkit.getDefaultToolkit();

			// Get the current screen size
			Dimension scrnsize = toolkit.getScreenSize();

			// if the directory does not exist, create it
			File dirScreenshots = new File(destDir);
			if (!dirScreenshots.exists()) {
				try {
					dirScreenshots.mkdir();
				} catch (SecurityException se) {
					// handle it
				}
			}

			// Capture the screen shot of the area of the screen defined by the
			// rectangle
			Robot robot = new Robot();

			// BufferedImage bi=robot.createScreenCapture(new
			// Rectangle(1280,1024));
			BufferedImage bi = robot
					.createScreenCapture(new Rectangle(scrnsize));
			ImageIO.write(bi, "png", new File(failFilePath));

			// Place a link to the screenshot in the report
			Reporter.log("Saved <a href=../" + failFilePath + ">Screenshot</a>");

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {

		}
	}
}
