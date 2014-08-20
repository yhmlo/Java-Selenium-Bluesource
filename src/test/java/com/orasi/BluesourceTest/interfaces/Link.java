package com.orasi.BluesourceTest.interfaces;

import com.orasi.BluesourceTest.interfaces.impl.LinkImpl;
import com.orasi.BluesourceTest.interfaces.impl.internal.ImplementedBy;

/**
 * Interface that wraps a WebElement in Button functionality. 
 */
@ImplementedBy(LinkImpl.class)
public interface Link extends Element {
    
 
    public void click();
}
