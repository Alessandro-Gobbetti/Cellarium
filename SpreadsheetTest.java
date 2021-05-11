

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SpreadsheetTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetTest {
    @Test
    public void TestSpreadsheet() {
        Spreadsheet s = new Spreadsheet();
        Cell c11 =  s.getOrCreate(1,1);
        Cell c12 =  s.getOrCreate(1,2);
        Cell c13 =  s.getOrCreate(1,3);
        c11.setFormula(new ConstFormula(10.0));
        c12.setFormula(new ScalingFormula(2.0, 1, 1));
        c13.setFormula(new ScalingFormula(3.0, 1, 2));
        assertEquals(60.0, s.getValue(1,3).asNumber());
        c11.setFormula(new ConstFormula(20.0));
        assertEquals(120.0, s.getValue(1,3).asNumber());
    }
}
