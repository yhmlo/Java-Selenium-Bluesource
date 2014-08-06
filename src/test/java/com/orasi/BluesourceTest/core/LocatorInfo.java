package com.orasi.BluesourceTest.core;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebElement;

public class LocatorInfo {
	 /**
     * Get the By Locator object used to create this element
     * @author Justin
     * @return {@link By} Return the By object to reuse
     */
    private static String getElementLocator(WebElement element){    	 


	    int startPosition = element.toString().lastIndexOf("->") + 3;

        String locator = element.toString().substring(startPosition, element.toString().lastIndexOf(":"));
        locator = locator.trim();
 	    return locator;

    }
     
     private static String getElementIdentifier(WebElement element){		
    	 int startPosition = element.toString().lastIndexOf(": ")+ 2;
    	 String locator = element.toString().substring(startPosition, element.toString().lastIndexOf("]"));
    			 
    	 	return locator.trim();
    	 }
     
     public static String getLocatorInfo(WebElement element){
    	 return getElementLocator(element) + " = " + getElementIdentifier(element);
     }
}
