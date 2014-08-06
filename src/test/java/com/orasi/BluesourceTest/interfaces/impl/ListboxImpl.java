package com.orasi.BluesourceTest.interfaces.impl;

import org.openqa.selenium.WebElement;
import com.orasi.BluesourceTest.interfaces.Listbox;
import com.orasi.BluesourceTest.core.LocatorInfo;
import org.testng.Reporter;

import java.util.List;

/**
 * Wrapper around a WebElement for the Select class in Selenium.
 */
public class ListboxImpl extends ElementImpl implements Listbox {
    private final org.openqa.selenium.support.ui.Select innerSelect;

    /**
     * Wraps a WebElement with checkbox functionality.
     *
     * @param element to wrap up
     */
    public ListboxImpl(WebElement element) {
        super(element);
        this.innerSelect = new org.openqa.selenium.support.ui.Select(element);
    }

  
    /**
     * Wraps Selenium's method.
     *
     * @param text visible text to select
     * @see org.openqa.selenium.support.ui.Select#selectByVisibleText(String)
     */
    @Override
    public void select(String text) {
        innerSelect.selectByVisibleText(text);
        Reporter.log("Select option [ " + text.toString() + " ] from Listbox [  @FindBy: " + LocatorInfo.getLocatorInfo(getWrappedElement()) + " ]");
    }

    /**
     * Wraps Selenium's method.
     *
     * @see org.openqa.selenium.support.ui.Select#deselectAll()
     */
    public void deselectAll() { 
        innerSelect.deselectAll();
    }

    /**
     * Wraps Selenium's method.
     *
     * @return list of all options in the select.
     * @see org.openqa.selenium.support.ui.Select#getOptions()
     */
    public List<WebElement> getOptions() {
        return innerSelect.getOptions();
    }

    /**
     * Wraps Selenium's method.
     *
     * @param text text to deselect by visible text
     * @see org.openqa.selenium.support.ui.Select#deselectByVisibleText(String)
     */
    public void deselectByVisibleText(String text) {
        innerSelect.deselectByVisibleText(text);
    }


    /**
     * Wraps Selenium's method.
     *
     * @return WebElement of the first selected option.
     * @see org.openqa.selenium.support.ui.Select#getFirstSelectedOption()
     */
    public WebElement getFirstSelectedOption() {
        return innerSelect.getFirstSelectedOption();
    }
    
    /**
     * 
     * @see org.openqa.selenium.WebElement#isSelected()
     */
    public boolean isSelected() {
        return ((WebElement) innerSelect).isSelected();
    }

}