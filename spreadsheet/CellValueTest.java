package spreadsheet;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class CellValueTest.
 * Tests methods of CellValue and his subclasses.
 *
 * @author  Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class CellValueTest {
    @Test 
    public void testEmptyCellValue() {
        EmptyCellValue emptyCellValue = new EmptyCellValue();
        assertEquals(CellType.EMPTY, emptyCellValue.type());
        assertEquals(true, emptyCellValue.isConvertibleToNumber());
        assertEquals(false, emptyCellValue.isError());        
        assertEquals(0.0, emptyCellValue.asNumber(), 0.0000000001);
        assertEquals("", emptyCellValue.asString());
    }
    
    @Test 
    public void testErrorCellValue() {
        ErrorCellValue errorCellValue = new ErrorCellValue("Err:LOOP", "The formula calls a cell depending on this.");
        assertEquals(CellType.ERROR, errorCellValue.type());
        assertEquals(false, errorCellValue.isConvertibleToNumber());
        assertEquals(true, errorCellValue.isError());        
        assertEquals(Double.NaN, errorCellValue.asNumber(), 0.0000000001);
        assertEquals("Err:LOOP", errorCellValue.asString());
        assertEquals("The formula calls a cell depending on this.", errorCellValue.getMessage());
    }
    
    @Test 
    public void testNumberCellValue() {
        NumberCellValue numberCellValue = new NumberCellValue(10.0);
        assertEquals(CellType.NUMBER, numberCellValue.type());
        assertEquals(true, numberCellValue.isConvertibleToNumber());
        assertEquals(false, numberCellValue.isError());        
        assertEquals(10.0, numberCellValue.asNumber(), 0.0000000001);
        assertEquals("10.0", numberCellValue.asString());
    }
    
    @Test 
    public void testStringCellValue() {
        StringCellValue stringCellValue = new StringCellValue("Troll");
        assertEquals(CellType.STRING, stringCellValue.type());
        assertEquals(false, stringCellValue.isConvertibleToNumber());
        assertEquals(false, stringCellValue.isError());        
        assertEquals(Double.NaN, stringCellValue.asNumber(), 0.0000000001);
        assertEquals("Troll", stringCellValue.asString());
    }
}
