package com.orasi.BluesourceTest.interfaces.impl;

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
        Reporter.log("Clear text from Textbox [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + " ]");
    }

    @Override
    public void set(String text) {
        if (text != ""){
        	getWrappedElement().clear();
        	getWrappedElement().sendKeys(text);
        	Reporter.log("Send Keys [ " + text.toString() + " ] to Textbox [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + " ]");
        	}else{
        		Reporter.log("Skipping input to Textbox [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + " ]");
        	}
    }

    public void safeSet(String text) {
        if (text != ""){
        	getWrappedElement().click();
        	getWrappedElement().sendKeys(Keys.CONTROL + "a");
        	getWrappedElement().sendKeys(Keys.DELETE);
        	getWrappedElement().sendKeys(text);
        	getWrappedElement().sendKeys(Keys.TAB);
        	Reporter.log("Send Keys [ " + text.toString() + " ] to Textbox [  @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + " ]");
        }else{
    		Reporter.log("Skipping input to Textbox [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + " ]");
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
