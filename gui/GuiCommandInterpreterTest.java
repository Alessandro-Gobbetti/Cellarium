package gui;

import commands.Command;
import spreadsheet.Spreadsheet;
import spreadsheet.Cell;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GuiCommandInterpreterTest.
 *
 * @author  Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandInterpreterTest {
    
    @Test
    public void testSetAndClear() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        //SET
        interpreter.parseAndExecute("SET C1 5", model);
        final Cell cell1 = spreadsheet.getOrCreate(0, 2);
        final double value1C1 = cell1.eval().asNumber();
        assertEquals(5.0, value1C1, 0.0);
        //REDO / UNDO
        interpreter.parseAndExecute("UNDO", model);
        interpreter.parseAndExecute("REDO", model);
        assertEquals(5.0, value1C1, 0.0);
        //CLEAR
        interpreter.parseAndExecute("CLEAR C1", model);
        final Cell cell = spreadsheet.getOrCreate(0, 2);
        final double value2 = cell.eval().asNumber();
        assertEquals(0.0, value2, 0.0);
        //REDO / UNDO
        interpreter.parseAndExecute("UNDO", model);
        interpreter.parseAndExecute("REDO", model);
        assertEquals(0.0, value2, 0.0);
        
        assertEquals("Ok!", interpreter.getLastOperationMessage());
    }
    
    
    @Test
    public void testFactorySet() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        final GuiCommandSetFactory command = new GuiCommandSetFactory();
        final Command set = command.getCommand("SET A1 9", model);
        assertEquals("Set", set.getName());
    }
    
    @Test
    public void testFactoryClear() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        final GuiCommandClearFactory command = new GuiCommandClearFactory();
        final Command clear = command.getCommand("CLEAR", model);
        assertEquals("Clear", clear.getName());
    }
    
    @Test
    public void testFactorySave() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        final GuiCommandSaveFactory command = new GuiCommandSaveFactory();
        final Command clear = command.getCommand("SAVE", model);
        assertEquals("Save", clear.getName());
    }
    
    @Test
    public void testFactoryOpen() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        final GuiCommandOpenFactory command = new GuiCommandOpenFactory();
        final Command clear = command.getCommand("OPEN", model);
        assertEquals("Open", clear.getName());
    }
    
    @Test
    public void testFactoryExport() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        final GuiCommandExportFactory command = new GuiCommandExportFactory();
        final Command clear = command.getCommand("EXPORT", model);
        assertEquals("Export", clear.getName());
    }
    
    @Test
    public void testFactoryImport() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        final GuiCommandImportFactory command = new GuiCommandImportFactory();
        final Command clear = command.getCommand("IMPORT", model);
        assertEquals("Import", clear.getName());
    }
    
    @Test
    public void testFactoryUndo() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        final GuiCommandUndoFactory command = new GuiCommandUndoFactory();
        final Command clear = command.getCommand("UNDO", model);
        assertEquals("UNDO", clear.getName());
    }
    
    @Test
    public void testFactoryRedo() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final GuiCommandInterpreter interpreter = new GuiCommandInterpreter();
        final SpreadsheetViewTableModel model = new SpreadsheetViewTableModel(spreadsheet, interpreter);
        final GuiCommandRedoFactory command = new GuiCommandRedoFactory();
        final Command clear = command.getCommand("REDO", model);
        assertEquals("REDO", clear.getName());
    }
}
