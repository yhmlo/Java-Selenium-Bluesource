package com.orasi.BluesourceTest.interfaces.impl;

import java.sql.Timestamp;

import com.orasi.BluesourceTest.interfaces.Link;
import com.orasi.BluesourceTest.core.LocatorInfo;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * Wraps a label on a html form with some behavior.
 */
public class LinkImpl extends ElementImpl implements Link {
	private java.util.Date date= new java.util.Date();
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public LinkImpl(WebElement element) {
        super(element);
    }

    @Override
    public void click() {
        getWrappedElement().click();
        Reporter.log(new Timestamp(date.getTime()) + " :: Click Link [ <b>@FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement())  + " </b> ]<br />");
    }
}