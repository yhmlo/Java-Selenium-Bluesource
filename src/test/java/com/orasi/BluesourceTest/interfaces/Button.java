package com.orasi.BluesourceTest.interfaces;

import com.orasi.BluesourceTest.interfaces.impl.ButtonImpl;
import com.orasi.BluesourceTest.interfaces.impl.internal.ImplementedBy;

/**
 * Interface that wraps a WebElement in Button functionality. 
 */
@ImplementedBy(ButtonImpl.class)
public interface Button extends Element {
	public void click();

}
