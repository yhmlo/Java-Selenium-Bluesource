package com.orasi.BluesourceTest.interfaces.impl;

import java.sql.Timestamp;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.orasi.BluesourceTest.interfaces.Textbox;
import com.orasi.BluesourceTest.interfaces.impl.ElementImpl;
import com.orasi.BluesourceTest.core.LocatorInfo;

import org.testng.Reporter;

/**
 * TextInput  wrapper.
 */
public class TextboxImpl extends ElementImpl implements Textbox {
    private WebElement element;
    private java.util.Date date= new java.util.Date();
	/**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public TextboxImpl(WebElement element) {
        super(element);
    }

    @Override
    public void clear() {
        getWrappedElement().clear();
		Reporter.log(new Timestamp(date.getTime()) + " :: Clear text from Textbox [<b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b>]<br />");
    }

    @Override
    public void set(String text) {
        if (text != ""){
        	getWrappedElement().clear();
        	getWrappedElement().sendKeys(text);
        	Reporter.log(new Timestamp(date.getTime()) + " :: Send Keys [ <b>" + text.toString() + "</b> ] to Textbox [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b> ]<br />");
        }else{
        	Reporter.log(new Timestamp(date.getTime()) + " :: Skipping input to Textbox [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b> ]<br />");
        }
    }

    public void safeSet(String text) {
        if (text != ""){
        	getWrappedElement().click();
        	getWrappedElement().sendKeys(Keys.CONTROL + "a");
        	getWrappedElement().sendKeys(Keys.DELETE);
        	getWrappedElement().sendKeys(text);
        	getWrappedElement().sendKeys(Keys.TAB);
        	Reporter.log(new Timestamp(date.getTime()) + " :: Send Keys [ <b>" + text.toString() + "</b> ] to Textbox [  <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b> ]<br />");
        }else{
        	Reporter.log(new Timestamp(date.getTime()) + " :: Skipping input to Textbox [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b> ]<br />");
    	}
    }
    
    /**
     * Gets the value of an input field.
     * @return String with the value of the field.
     */
    @Override
    public String getText() {
        return getWrappedElement().getAttribute("value");
    }
}
