package com.orasi.BluesourceTest.interfaces.impl;


import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orasi.BluesourceTest.interfaces.Element;
import com.orasi.BluesourceTest.core.LocatorInfo;
import com.orasi.BluesourceTest.core.CONSTANTS;
import com.orasi.BluesourceTest.tests.BaseTest;
import org.testng.Reporter;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * An implementation of the Element interface. Delegates its work to an underlying WebElement instance for
 * custom functionality.
 */
public class ElementImpl implements Element {

    private final WebElement element;
    private java.util.Date date= new java.util.Date();
    private java.util.Date dateAfter = new java.util.Date();
    
    public ElementImpl(final WebElement element) {
        this.element = element;              
    }

    /**
     * 
     * @see org.openqa.selenium.WebElement#click()
     */
    public void click() {
        element.click();
        Reporter.log(new Timestamp(date.getTime()) + " :: Clicked [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>]<br />");
    }

    /**
     * 
     * @see org.openqa.selenium.WebElement#getLocation()
     */
    public Point getLocation() {
        return element.getLocation();
    }

    /**
     * 
     * @see org.openqa.selenium.WebElement#submit()
     */
    public void submit() {
        element.submit();
    }

    /**
     * 
     * @see org.openqa.selenium.WebElement#getAttribute()
     */    
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

     /**
      * 
      * @see org.openqa.selenium.WebElement#getCssValue()
      */
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }


    /**
     * 
     * @see org.openqa.selenium.WebElement#getSize()
     */
    public Dimension getSize() {
        return element.getSize();
    }


    /**
     * 
     * @see org.openqa.selenium.WebElement#findElements()
     */
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    /**
     * 
     * @see org.openqa.selenium.WebElement#getText()
     */
    public String getText() {
        return element.getText();
    }


    /**
     * 
     * @see org.openqa.selenium.WebElement#getTagName()
     */
    public String getTagName() {
        return element.getTagName();
    }


    /**
     * 
     * @see org.openqa.selenium.WebElement#findElement()
     */
    public WebElement findElement(By by) {
        return element.findElement(by);
    }


    /**
     * 
     * @see org.openqa.selenium.WebElement#isEnabled()
     */
    public boolean isEnabled() {
        return element.isEnabled();
    }


    /**
     * 
     * @see org.openqa.selenium.WebElement#isDisplayed()
     */
    public boolean isDisplayed() {
        return element.isDisplayed();
    }


    public boolean isSelected() {	
    	return element.isSelected();
    }
    
    /**
     * 
     * @see org.openqa.selenium.WebElement#clear()
     */
    public void clear() {
        element.clear();
    }
    
    /**
     * 
     * @see org.openqa.selenium.WebElement#sendKeys()
     */
    public void sendKeys(CharSequence... keysToSend) {    
    	if (keysToSend.toString() != ""){
    		element.sendKeys(keysToSend);    		
    		Reporter.log(new Timestamp(date.getTime()) + " :: Send Keys [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] to element [ <b>" + element + "</b> ]<br />");
    	}
    }

    /**
     * 
     * @see org.openqa.selenium.WebElement#getWrappedElement()
     */
    public WebElement getWrappedElement() {
        return element;
    }


    /**
     * 
     * @see org.openqa.selenium.internal.Locatable#getCoordinates();
     */
   public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public boolean elementWired() {
        return (element != null);
    }


    /**
     *
     * Used in conjunction with WebObjectPresent to determine if the desired element is present in the DOM
     * Will loop for the time out listed in org.orasi.chameleon.CONSTANT.TIMEOUT
     * If object is not present within the time, throw an error
     * @author Justin
     */
     public boolean syncPresent(WebDriver driver) {
    	 boolean found = false;
     	double loopTimeout = 0;
     	By locator = getElementLocator();
     	loopTimeout = CONSTANTS.TIMEOUT*10;
		Reporter.log(new Timestamp(date.getTime()) + " ::<i> Syncing to element [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + "</b> ] to be <b>PRESENT</b> in DOM within [ " + CONSTANTS.TIMEOUT + " ] seconds.</i><br />");
     	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
     		
     		if (webElementPresent(driver, locator)){
     		    found = true;
     		    break;
     		}
     		try {
     			Thread.sleep(100);
     			
     		} catch (Exception e) {
     		}
     		 
     	}
     	
     	if (!found){
     		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>PRESENT</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not PRESENT on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
     	return found;
     }	 


     /**
      *
      * Used in conjunction with WebObjectPresent to determine if the desired element is present in the DOM
      * Will loop for the time out passed in parameter timeout
      * If object is not present within the time, throw an error
      * @author Justin
      */
      public boolean syncPresent(WebDriver driver, int timeout) {
    	  boolean found = false;
      	double loopTimeout = 0;
      	By locator = getElementLocator();
      	loopTimeout = timeout*10;
      	Reporter.log(new Timestamp(date.getTime()) + " ::<i> Syncing to element [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + "</b> ] to be <b>PRESENT</b> in DOM within [ " + timeout + " ] seconds.</i><br />");
      	 
      	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
      		
      		if (webElementPresent(driver, locator)){
      		    found = true;
      		    break;
      		}
      		try {
      			Thread.sleep(100);
      			
      		} catch (Exception e) {
      		}
      		 
      	}
      	
      	if (!found){
      		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>PRESENT</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not PRESENT on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
      	return found;
      }	 


      /**
       *
       * Used in conjunction with WebObjectPresent to determine if the desired element is present in the DOM
       * Will loop for the time out passed in parameter timeout
       * If object is not present within the time, handle error based on returnError
       * @author Justin
       */
       public boolean syncPresent(WebDriver driver, int timeout, boolean returnError) {
    	   boolean found = false;
       	double loopTimeout = 0;
       	By locator = getElementLocator();
       	loopTimeout = timeout*10;
       	Reporter.log(new Timestamp(date.getTime()) + " ::<i> Syncing to element [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + "</b> ] to be <b>PRESENT</b> in DOM within [ " + timeout + " ] seconds.</i><br />");
       	 
       	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
       		
       		if (webElementPresent(driver, locator)){
       		    found = true;
       		    break;
       		}
       		try {
       			Thread.sleep(100);
       			
       		} catch (Exception e) {
       		}
       		 
       	}
       	
       	if (!found && returnError){
       		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>PRESENT</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not PRESENT on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;
       }	 

   /**
    *
    * Used in conjunction with WebObjectVisible to determine if the desired element is visible on the screen 
    * Will loop for the time out listed in org.orasi.chameleon.CONSTANT.TIMEOUT
    * If object is not visible within the time, throw an error
    * @author Justin
    */
    public boolean syncVisible(WebDriver driver) {
    	boolean found = false; 
    	double loopTimeout = 0;
    	
    	loopTimeout = CONSTANTS.TIMEOUT*10;
    	
    	Reporter.log(new Timestamp(date.getTime()) + " ::<i> Syncing to element [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + "</b> ] to be <b>VISISBLE</b> within [ " + CONSTANTS.TIMEOUT + " ] seconds.</i><br />");
    	 
    	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
    		
    		if (webElementVisible(driver, element)){
    		    found = true;
    		    break;
    		}
    		try {
    			Thread.sleep(100);
    			
    		} catch (Exception e) {
    		}
    		 
    	}
    	
    	if(!found){
    		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>VISIBLE</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not VISIBLE on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;
    	 
    }	 
    
    
   /**
    * 
    * Used in conjunction with WebObjectVisible to determine if the desired element is visible on the screen 
    * Will loop for the time out passed in the variable timeout
    * If object is not visible within the time, throw an error
    * @author Justin
    * 
    */
    public boolean syncVisible(WebDriver driver, int timeout) {
    	boolean found = false;
    	double loopTimeout = 0;
    	
    	loopTimeout = Long.valueOf(timeout)*10;	
    	Reporter.log(new Timestamp(date.getTime()) + " ::<i> Syncing to element [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + "</b> ] to be <b>VISISBLE</b> within [ " + timeout + " ] seconds.</i><br />");
    	 
    	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
    		
    		if (webElementVisible(driver, element)){
    			found = true;
    			break;
    		}
    		try {
    			Thread.sleep(100);
    			
    		} catch (Exception e) {
    		}
    		 
    	}
    	
    	if(!found){
    		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>VISIBLE</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not VISIBLE on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;	
    }	 

    /**
     * Used in conjunction with WebObjectVisible to determine if the desired element is visible on the screen 
     * Will loop for the time out passed in the variable timeout
     * If object is not visible within the time, handle the error based on the boolean
     *
     * @author Justin
     *
     */
    public boolean syncVisible(WebDriver driver, int timeout, boolean returnError) {
    	boolean found = false;
    	double loopTimeout = 0;
    	
    
    	loopTimeout = Integer.valueOf(timeout)*10;    	
		Reporter.log(new Timestamp(date.getTime()) + " :: <i>Syncing to element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>VISIBLE/<b> within [ " + timeout + " ] seconds.</i><br />");
    			 
    	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
    		
    		if (webElementVisible(driver, element)){
    			found = true;
    			break;
    		}
    		try {
    			Thread.sleep(100);				
    		} catch (Exception e) {
    		}
    		 
    	}
    	
    	 if(!found && returnError){
    		dateAfter= new java.util.Date();
     		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>VISIBLE</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
     		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not VISIBLE on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
     	}
		return found;	
    }	 
    

    /**
     * Used in conjunction with WebObjectVisible to determine if the desired element is hidden from the screen 
     * Will loop for the time out listed in org.orasi.chameleon.CONSTANT.TIMEOUT
     * If object is not visible within the time, throw an error
     * @author Justin
     * */
    public boolean syncHidden(WebDriver driver){
    	boolean found = false;
    	long loopTimeout = 0;
    	double seconds;
    	 
    	loopTimeout = CONSTANTS.TIMEOUT * 10;    	
     	Reporter.log(new Timestamp(date.getTime()) + " :: <i> Syncing to element [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>HIDDEN</b> within [ " + CONSTANTS.TIMEOUT + " ] seconds.</i><br />");
    	 driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    	for( seconds =0 ; seconds < loopTimeout ; seconds+=1){
    		
            if (!webElementVisible(driver, element)){
            	found = true;
            	break;
            }
            try {
            	Thread.sleep(100);
            } catch (Exception e) {
            
            }
    		 
    	}
    	driver.manage().timeouts().implicitlyWait(CONSTANTS.TIMEOUT, TimeUnit.SECONDS);
    	if(!found){
    		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>HIDDEN</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not HIDDEN on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;
    }	 

   
    /** 
     * Used in conjunction with WebObjectVisible to determine if the desired element is hidden from the screen 
     * Will loop for the time out listed in org.orasi.chameleon.CONSTANT.TIMEOUT
     * If object is not visible within the time, throw an error
     * @author Justin
     */
    public boolean syncHidden(WebDriver driver, int timeout){
    	boolean found = false;
    	long loopTimeout = 0;
    	double seconds;

    	loopTimeout = Long.valueOf(timeout)*10;	
     	Reporter.log(new Timestamp(date.getTime()) + " :: <i>Syncing to element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>HIDDEN</b> within [ <b>" + timeout + "</b> ] seconds.</i><br />");
    	
    	for(seconds =0 ; seconds < loopTimeout ; seconds+=1){
    		
            if (!webElementVisible(driver, element)){
            	found = true;
            	break;
            }
            try {
            	Thread.sleep(100);
            } catch (Exception e) {
            
            }
	 
    	}
    	
    	if(!found){
      		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>HIDDEN</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not HIDDEN on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;		
    }	 


    /**
     * Used in conjunction with WebObjectVisible to determine if the desired element is visible on the screen 
     * Will loop for the time out passed in the variable timeout
     * If object is not visible within the time, handle the error based on the boolean
     * @author Justin
     */
    public boolean syncHidden(WebDriver driver,int timeout, boolean returnError){
    	boolean found = false;
    	long loopTimeout = 0;
    	 		
    	loopTimeout = Long.valueOf(timeout)*10;		
    	Reporter.log(new Timestamp(date.getTime()) + " :: <i>Syncing to element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>HIDDEN</b> within [ <b>" + timeout + "</b> ] seconds.</i><br />");
    	 
    	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
    		
    		if (!webElementVisible(driver, element)){
    			found = true;
    			break;
    		}
    		try {
    			Thread.sleep(100);
    		} catch (Exception e) {
    		}
    		 
    	}
    	
    	 if(!found && returnError){    		
    		dateAfter= new java.util.Date();
     		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>HIDDEN</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
     		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not HIDDEN on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
     		}
		return found;	
    	}	 

    /**
     *
     * Used in conjunction with WebObjectEnabled to determine if the desired element is enabled on the screen 
     * Will loop for the time out listed in org.orasi.chameleon.CONSTANT.TIMEOUT
     * If object is not enabled within the time, throw an error
     * @author Justin
     */
     public boolean syncEnabled(WebDriver driver) {
     	boolean found = false; 
 	double loopTimeout = 0;
     	
     	loopTimeout = CONSTANTS.TIMEOUT*10;
     	Reporter.log(new Timestamp(date.getTime()) + " :: <i>Syncing to element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>ENABLED</b> within [ <b>" + CONSTANTS.TIMEOUT+ "</b> ] seconds.</i><br />");
     	 
     	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
     		
     		if (webElementEnabled(driver, element)){
     		    found = true;
     		    break;
     		}
     		try {
     			Thread.sleep(100);
     			
     		} catch (Exception e) {
     		}
     		 
     	}
     	
     	if(!found){
     		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>ENABLED</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not ENABLED on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;
     	 
     }	 
     
     
    /**
     * 
     * Used in conjunction with WebObjectEnabled to determine if the desired element is enabled on the screen 
     * Will loop for the time out passed in the variable timeout
     * If object is not enabled within the time, throw an error
     * @author Justin
     * 
     */
     public boolean syncEnabled(WebDriver driver, int timeout) {
     	boolean found = false;
     	double loopTimeout = 0;
     	
     	loopTimeout = Long.valueOf(timeout)*10;	
     	Reporter.log(new Timestamp(date.getTime()) + " :: <i>Syncing to element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>ENABLED</b> within [ <b>" + timeout + "</b> ] seconds.</i><br />");
     	 
     	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
     		
     		if (webElementEnabled(driver, element)){
     			found = true;
     			break;
     		}
     		try {
     			Thread.sleep(100);
     			
     		} catch (Exception e) {
     		}
     		 
     	}
     	
     	if(!found){
     		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>ENABLED</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not ENABLED on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;	
     }	 

     /**
      * Used in conjunction with WebObjectEnabled to determine if the desired element is enabled on the screen 
      * Will loop for the time out passed in the variable timeout
      * If object is not enabled within the time, handle the error based on the boolean
      *
      * @author Justin
      *
      */
     public boolean syncEnabled(WebDriver driver, int timeout, boolean returnError) {
     	boolean found = false;
     	double loopTimeout = 0;
     	
     
     	loopTimeout = Integer.valueOf(timeout)*10;
     	Reporter.log(new Timestamp(date.getTime()) + " :: <i>Syncing to element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>ENABLED</b> within [ <b>" + timeout + "</b> ] seconds.</i><br />");
     			 
     	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
     		
     		if (webElementEnabled(driver, element)){
     			found = true;
     			break;
     		}
     		try {
     			Thread.sleep(100);				
     		} catch (Exception e) {
     		}
     		 
     	}
     	
     	 if(!found && returnError){
     		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>ENABLED</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not ENABLED on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;	
     }	 
     

     /**
      *
      * Used in conjunction with WebObjectEnabled to determine if the desired element is disabled on the screen 
      * Will loop for the time out listed in org.orasi.chameleon.CONSTANT.TIMEOUT
      * If object is not disabled  within the time, throw an error
      * @author Justin
      */
      public boolean syncDisabled(WebDriver driver) {
      	boolean found = false; 
      	double loopTimeout = 0;
      	
      	loopTimeout = CONSTANTS.TIMEOUT*10;
      	Reporter.log(new Timestamp(date.getTime()) + " :: <i>Syncing to element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>DISABLED</b> within [ <b>" + CONSTANTS.TIMEOUT + "</b> ] seconds.</i><br />");
      	 
      	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
      		
      		if (!webElementEnabled(driver, element)){
      		    found = true;
      		    break;
      		}
      		try {
      			Thread.sleep(100);
      			
      		} catch (Exception e) {
      		}
      		 
      	}
      	
      	if(!found){
      		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>DISABLED</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not DISABLED on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;
      	 
      }	 
      
      
     /**
      * 
      * Used in conjunction with WebObjectDisabled to determine if the desired element is disabled on the screen 
      * Will loop for the time out passed in the variable timeout
      * If object is not disabled within the time, throw an error
      * @author Justin
      * 
      */
      public boolean syncDisabled(WebDriver driver, int timeout) {
      	boolean found = false;
      	double loopTimeout = 0;
      	
      	loopTimeout = Long.valueOf(timeout)*10;	
      	Reporter.log(new Timestamp(date.getTime()) + " :: <i>Syncing to element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>DISABLED</b> within [ <b>" + timeout + "</b> ] seconds.</i><br />");
      	 
      	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
      		
      		if (!webElementEnabled(driver, element)){
      			found = true;
      			break;
      		}
      		try {
      			Thread.sleep(100);
      			
      		} catch (Exception e) {
      		}
      		 
      	}
      	
      	if(!found){
      		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>DISABLED</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not DISABLED on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;	
      }	 

      /**
       * Used in conjunction with WebObjectDisabled to determine if the desired element is disabled on the screen 
       * Will loop for the time out passed in the variable timeout
       * If object is not disabled within the time, handle the error based on the boolean
       *
       * @author Justin
       *
       */
      public boolean syncDisabled(WebDriver driver, int timeout, boolean returnError) {
      	boolean found = false;
      	double loopTimeout = 0;
      	
      
      	loopTimeout = Integer.valueOf(timeout)*10;
      	Reporter.log(new Timestamp(date.getTime()) + " :: <i>Syncing to element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + "</b> ] to be <b>DISABLED</b> within [ <b>" + timeout + "</b> ] seconds.</i><br />");
      			 
      	for(double seconds =0 ; seconds < loopTimeout ; seconds+=1){
      		
      		if (!webElementEnabled(driver, element)){
      			found = true;
      			break;
      		}
      		try {
      			Thread.sleep(100);				
      		} catch (Exception e) {
      		}
      		 
      	}
      	
      	 if(!found && returnError){
      		dateAfter= new java.util.Date();
    		Reporter.log(new Timestamp(dateAfter.getTime()) + " :: <i>Element [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>] is not <b>DISABLED</b> on the page after [ " + (dateAfter.getTime()- date.getTime()) / 1000.0 + " ] seconds.</i><br />");
    		throw new RuntimeException(new Timestamp(date.getTime()) + ":: Element [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " ] is not DISABLED on the page after [ "+ (dateAfter.getTime()- date.getTime()) / 1000.0  +" ] seconds.");
    	}
		return found;	
      }	 
      
    
   /**
    * Use WebDriver Wait to determine if object is present in the DOM or not
    * @author Justin
    * @param driver Main WebDriver
    * @param locator {@link By} object to search for
    * @return TRUE if element is currently present in the DOM, FALSE if the element is not present in the DOM
    */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private boolean webElementPresent(WebDriver driver, By locator){
	 Wait wait  = new WebDriverWait(driver, 0);		

	 try{			     	
	     	return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null;		
	     }catch (NoSuchElementException |  ClassCastException | StaleElementReferenceException | TimeoutException  e){
		return false;
	     }			

	 }
	     
    /**
     * Use WebDriver Wait to determine if object is visible on the screen or not
     * @author Justin
     * @param driver Main WebDriver
     * @param element Element to search for
     * @return TRUE if element is currently visible on the screen, FALSE if the element is not visible on the screen
     */
     @SuppressWarnings({ "unchecked", "rawtypes" })
     private boolean webElementVisible(WebDriver driver, WebElement element){
	 Wait wait  = new WebDriverWait(driver, 0);		
	 Point location =null;
	 try{		
		 location= element.getLocation();
		 if (location.getX() > 0 || location.getY() > 0){
			 return true;
		 }
	     	//return wait.until(ExpectedConditions.visibilityOf(element)) != null;
		 
	     }catch ( NoSuchElementException |  ClassCastException | StaleElementReferenceException |TimeoutException  e){
	    	// e.printStackTrace();
		return false;
	     }			
	 return false;
	 }
	 
     /**
      * Use WebDriver Wait to determine if object is enabled on the screen or not
      * @author Justin
      * @param driver Main WebDriver
      * @param element Element to search for
      * @return TRUE if element is currently enabled on the screen, FALSE if the element is not enabled on the screen
      */
      @SuppressWarnings({ "unchecked", "rawtypes" })
      private boolean webElementEnabled(WebDriver driver, WebElement element){
 	 Wait wait  = new WebDriverWait(driver, 0);		

 	 try{		
 	     	//return wait.until(ExpectedConditions.elementToBeClickable(element)) != null;
 		 return element.isEnabled();
 	     }catch (NoSuchElementException |  ClassCastException | StaleElementReferenceException | TimeoutException  e){
 		return false;
 	     }			
  
 	 }
 	 
    /**
     * Get the By Locator object used to create this element
     * @author Justin
     * @return {@link By} Return the By object to reuse
     */
    public By getElementLocator(){    	 
	By by = null;
	String locator = "";
	int startPosition = 0;
	try{
		if (BaseTest.browserUnderTest == "HTMLUnitDriver"){
	    	//<input type="text" name="USER" />		
			startPosition = element.toString().indexOf("=\"");
			locator = element.toString().substring(startPosition, element.toString().indexOf("="));
		}else{
			startPosition = element.toString().lastIndexOf("->") + 3;
			locator = element.toString().substring(startPosition, element.toString().lastIndexOf(":"));        	
		}
	        locator = locator.trim();
            switch (locator){
            	case "className":
            	    by = new ByClassName(getElementIdentifier());
            	    break;
            	case "cssSelector":
            	    by = By.cssSelector(getElementIdentifier());
            	    break;
            	case "id":
            	    by = By.id(getElementIdentifier());
            	    break;
            	case "linkText":
            	    by = By.linkText(getElementIdentifier());
            	    break;
            	case "name":
            	    by = By.name(getElementIdentifier());
            	    break;
            	case "tagName":
            	    by = By.tagName(getElementIdentifier());
            	    break;
            	case "xpath":
            	    by = By.xpath(getElementIdentifier());
            	    break;
            }
            return by;
	}catch(Exception e){
	    e.printStackTrace();
	    return null;
	}		
    }
     
     public String getElementIdentifier(){	
    	String locator = "";
    	int startPosition = 0;
    	 
		if (BaseTest.browserUnderTest == "HTMLUnitDriver"){
	    	//<input type="text" name="USER" />		
			startPosition = element.toString().lastIndexOf("=\"");
			locator = element.toString().substring(startPosition, element.toString().indexOf("\" /"));
		}else{
			 startPosition = element.toString().lastIndexOf(": ")+ 2;
	    	 locator = element.toString().substring(startPosition, element.toString().lastIndexOf("]"));       	
		}
	
			 
	 	return locator.trim();
	 }
}
