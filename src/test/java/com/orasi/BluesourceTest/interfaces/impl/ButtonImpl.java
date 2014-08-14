package com.orasi.BluesourceTest.interfaces.impl;

import java.sql.Timestamp;

import org.openqa.selenium.WebElement;

import com.orasi.BluesourceTest.interfaces.Button;
import com.orasi.BluesourceTest.core.LocatorInfo;

import org.testng.Reporter;

/**
 * Wraps a label on a html form with some behavior.
 */
public class ButtonImpl extends ElementImpl implements Button {
	private java.util.Date date= new java.util.Date();
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public ButtonImpl(WebElement element) {
        super(element);
    }
    
    @Override
    public void click() {
        getWrappedElement().click();
        Reporter.log(new Timestamp(date.getTime()) + " :: Click Button [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + " ]<br />");
    }

//    @Override
//    public String getFor() {
//        return getWrappedElement().getAttribute("for");
//    }
}
