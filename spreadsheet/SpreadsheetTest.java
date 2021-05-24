package spreadsheet;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SpreadsheetTest.
 *
 * @author  (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public class SpreadsheetTest {
    @Test
    public void TestSpreadsheet() {
        Spreadsheet s = new Spreadsheet();
        Cell c11 =  s.getOrCreate(1,1);
        Cell c12 =  s.getOrCreate(1,2);
        Cell c13 =  s.getOrCreate(1,3);
        
        c11.setFormula(new Literal(10.0));
        c12.setFormula(new Addition(new Literal(5.0), new CellReference(false, 1, false, 1)));
        c13.setFormula(new Multiplication(new CellReference(false, 1, false, 1), new CellReference(false, 1, false, 2)));

        assertEquals(150, s.getValue(1,3).asNumber(), 0.0);
        c11.setFormula(new Literal(20.0));
        assertEquals(500, s.getValue(1,3).asNumber(), 0.0);
    }
    
    @Test
    public void TestRemove() {
        Spreadsheet s = new Spreadsheet();
        Cell c11 = s.getOrCreate(1,1);
        Cell c21 = s.getOrCreate(2,1);
        assertTrue(s.exists(1,1));
        assertTrue(s.exists(2,1));
        s.remove(1,1);
        s.remove(2,1);
        s.remove(1,2);
        assertFalse(s.exists(1,1));
        assertFalse(s.exists(2,1));
    }
    
    @Test
    public void TestRowAndColFromIndex() {
        Spreadsheet s = new Spreadsheet();
        Cell c11 = s.getOrCreate(3,5);
        int index = s.indexFromRowCol(3,5);
        assertEquals(5, s.colFromIndex(index));
        assertEquals(3, s.rowFromIndex(index));
    }
    
    // @Test
    // public void TestLoop() {
        // Spreadsheet s = new Spreadsheet();
        // Cell c11 = s.getOrCreate(1,1);
        // Cell c12 =  s.getOrCreate(1,2);
        // c11.setFormula(new ScalingFormula(2.0, 1, 2));
        // c12.setFormula(new ScalingFormula(2.0, 1, 1));
        // assertEquals("#VALUE", s.getValue(1,2).asString());
        // assertEquals("#VALUE", s.getValue(1,1).asString());
    // }
    
    @Test
    public void TestParsedSpreadsheet() {
        Spreadsheet s = new Spreadsheet();
        Parser p = new CellariumParser();
        Cell cA1 =  s.getOrCreate(0,0);
        Cell cA2 =  s.getOrCreate(1,0);
        Cell cA3 =  s.getOrCreate(2,0);
        Cell cA4 =  s.getOrCreate(3,0);
        
        cA1.setFormula(p.parse("10"));
        cA2.setFormula(p.parse("= A1 + 5.0"));
        cA3.setFormula(p.parse("= A1 * A2"));
        cA4.setFormula(p.parse("=AVERAGE(A1:A3)"));

        assertEquals(150, s.getValue(2, 0).asNumber(), 0.0);
        assertEquals((10.0+15.0+150.0)/3.0, s.getValue(3, 0).asNumber(), 0.000001);
        cA1.setFormula(p.parse("= +20"));
        assertEquals(500, s.getValue(2, 0).asNumber(), 0.0);
    }
}
