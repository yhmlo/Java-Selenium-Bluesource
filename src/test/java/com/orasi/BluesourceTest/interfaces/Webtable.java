package com.orasi.BluesourceTest.interfaces;

import org.openqa.selenium.WebDriver;



import com.orasi.BluesourceTest.interfaces.impl.WebtableImpl;
import com.orasi.BluesourceTest.interfaces.impl.internal.ImplementedBy;

/**
 * Interface that wraps a WebElement in CheckBox functionality.
 */
@ImplementedBy(WebtableImpl.class)
public interface Webtable extends Element {

    /**
     * Get the row count of the Webtable
     */
    int getRowCount();

    /**
     * Get the column count for the Webtable on a specified Row
     */
    int getColumnCount(WebDriver driver, int row);

    /**
     * Get cell data of the specified row and Column in a Webtable
     */
    String getCellData( WebDriver driver, int row, int column);

    /**
     * Get Row number where text is found
     */
    int getRowWithCellText(WebDriver driver, String text);

    /**
     * Get Row number where text is found in a specific column
     */    
    int getRowWithCellText( WebDriver driver, String text, int columnPosition);

    /**
     * Get Row number where text is found in a specific column and starting row
     */    
    int getRowWithCellText( WebDriver driver, String text, int columnPosition, int startRow);
    
    /**
     * Get Column number where text is found
     */  
    int getColumnWithCellText(WebDriver driver, String text);
    
    /**
     * Get Column number where text is found in a specific row
     */  
    int getColumnWithCellText(WebDriver driver, String text, int rowPosition);
}
