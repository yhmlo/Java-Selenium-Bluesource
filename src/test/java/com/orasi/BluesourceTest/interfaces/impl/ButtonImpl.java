package com.orasi.BluesourceTest.interfaces.impl;

import org.openqa.selenium.WebElement;
import com.orasi.BluesourceTest.interfaces.Button;
import com.orasi.BluesourceTest.core.LocatorInfo;
import org.testng.Reporter;

/**
 * Wraps a label on a html form with some behavior.
 */
public class ButtonImpl extends ElementImpl implements Button {

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
        Reporter.log("Click Button [ @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + " ]");
    }

//    @Override
//    public String getFor() {
//        return getWrappedElement().getAttribute("for");
//    }
}
