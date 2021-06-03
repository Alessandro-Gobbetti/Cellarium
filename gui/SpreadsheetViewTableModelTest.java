package gui;

//import commands.Command;
import spreadsheet.Spreadsheet;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SpreadsheetViewTableModelTest.
 * Tests the methods of SpreadsheetViewTableModel.
 *
 * @author  Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetViewTableModelTest {
    @Test 
    public void testGetValue() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        
        //getValueAt + setValueAt
        final Object valueAt00 = model.getValueAt(0,0);
        assertEquals("" , valueAt00);
        model.setValueAt("Hallo", 1, 1);
        final Object valueAtA1 = model.getValueAt(1,1);
        assertEquals("Hallo" , valueAtA1);
        model.setValueAt(valueAtA1, 0, 1);
        final Object valueAtA0 = model.getValueAt(0,1);
        // Get Cell in column index
        assertEquals("A" , valueAtA0); 
        model.setValueAt(valueAtA0, 4, 0);
        final Object valueAt4 = model.getValueAt(4,0);
        assertEquals("4", valueAt4);
        //Clear
        model.clear();
        //Check if cleared
        final Object valueA1 = model.getValueAt(1,1);
        assertEquals("" , valueA1);
        
        //Get Cell out of spreadsheet
        final Object value = model.getValueAt(-1,-1);
        assertEquals("" , value);
        
    }
    
    @Test
    public void testGetFromula() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        
        //GetFormula
        final Object formulaAt00 = model.getFormulaAt(0,0);
        assertEquals("" , formulaAt00);
        model.setValueAt("Hallo", 1, 1);
        final Object formulaAtA1 = model.getFormulaAt(1,1);
        assertEquals("Hallo" , formulaAtA1);
        final Object formula = model.getFormulaAt(-1,-1);
        assertEquals("" , formula);
    }
    
    @Test
    public void testIsNumber() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        
        //IsNumber
        assertFalse(model.isNumberAt(1,1));
        model.setValueAt("9", 1, 1);
        assertTrue(model.isNumberAt(1,1));
        assertFalse(model.isNumberAt(0,0));
        assertFalse(model.isNumberAt(1,0));
        assertFalse(model.isNumberAt(-1,-1));
    }
    
    @Test
    public void testIsError() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        
        //IsError
        assertFalse(model.isErrorAt(1,1));
        model.setValueAt("9", 1, 1);
        assertFalse(model.isErrorAt(1,1));
        assertFalse(model.isErrorAt(0,0));
        assertFalse(model.isErrorAt(-1,-1));
    }
    
    @Test 
    public void testOrigin() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        
        //Origin Row
        assertEquals(1, model.getOriginRow());
        assertEquals(1, model.getOriginCol());
        model.setOrigin(3,5);
        assertEquals(3, model.getOriginRow());
        assertEquals(5, model.getOriginCol());
        model.setOrigin(1,1);
    }
    
    @Test 
    public void testEditable() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        
        //isCellEditable
        assertTrue(model.isCellEditable(3,5));
        assertFalse(model.isCellEditable(0,0));
        assertFalse(model.isCellEditable(0,1));
        assertFalse(model.isCellEditable(3,0));
    }
    
    @Test
    public void testDimension() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        
        //MaxDimension
        final int rowDimension = model.getSpreadsheetRowDimension();
        final int rowCount = model.getRowCount();
        assertEquals(rowCount , rowDimension);
        final int colDimension = model.getSpreadsheetColDimension();
        final int colCount = model.getColumnCount();
        assertEquals(colCount , colDimension);
        model.setValueAt("9", 70, 80);
        final int rowDimension2 = model.getSpreadsheetRowDimension();
        assertEquals(69 , rowDimension2);
        final int colDimension2 = model.getSpreadsheetColDimension();
        assertEquals(79 , colDimension2);
    }
}
