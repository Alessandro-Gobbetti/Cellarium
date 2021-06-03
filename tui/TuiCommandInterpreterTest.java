package tui;

import commands.Command;
import spreadsheet.Spreadsheet;
import spreadsheet.Cell;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TuiCommandInterpreterTest.
 * Tests the methods in the Tui package.
 *
 * @author  Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandInterpreterTest {
    
    @Test
    public void Set1Test() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandInterpreter interpreter = new TuiCommandInterpreter();
        interpreter.parseAndExecute("SET C1 5", spreadsheet);
        //final int col = CellReference.fromAlpha26("C");
        final Cell cell = spreadsheet.getOrCreate(0, 2);
        final double value = cell.eval().asNumber();
        assertEquals(5.0, value, 0.0);
    }
    
    @Test
    public void Set2Test() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandInterpreter interpreter = new TuiCommandInterpreter();
        interpreter.parseAndExecute("SET C1 5", spreadsheet);
        interpreter.parseAndExecute("SET C3 8", spreadsheet);
        interpreter.parseAndExecute("SET A1 = C1 + C3", spreadsheet);
        //final int colC = CellReference.fromAlpha26("C");
        //final int colA = CellReference.fromAlpha26("A");
        final Cell cellC1 = spreadsheet.getOrCreate(0, 2);
        final Cell cellC3 = spreadsheet.getOrCreate(2, 2);
        final Cell cellA1 = spreadsheet.getOrCreate(0, 0);
        final double valueC1 = cellC1.eval().asNumber();
        final double valueC3 = cellC3.eval().asNumber();
        final double valueA1 = cellA1.eval().asNumber();
        assertEquals(5.0, valueC1, 0.0);
        assertEquals(8.0, valueC3, 0.0);
        assertEquals(13.0, valueA1, 0.0);
    }
    
    @Test
    public void Clear1Test() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandInterpreter interpreter = new TuiCommandInterpreter();
        interpreter.parseAndExecute("SET C1 5", spreadsheet);
        //final int col = CellReference.fromAlpha26("C");
        final Cell cell1 = spreadsheet.getOrCreate(0, 2);
        final double value1 = cell1.eval().asNumber();
        assertEquals(5.0, value1, 0.0);
        interpreter.parseAndExecute("CLEAR C1", spreadsheet);
        final Cell cell2 = spreadsheet.getOrCreate(0, 2);
        final double value2 = cell2.eval().asNumber();
        assertEquals(0.0, value2, 0.0);
    }
    
    
    @Test
    public void Clear2Test() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandInterpreter interpreter = new TuiCommandInterpreter();
        interpreter.parseAndExecute("SET C1 5", spreadsheet);
        interpreter.parseAndExecute("SET C3 8", spreadsheet);
        interpreter.parseAndExecute("SET A1 = C1 + C3", spreadsheet);
        //final int colC = CellReference.fromAlpha26("C");
        //final int colA = CellReference.fromAlpha26("A");
        final Cell cellC1 = spreadsheet.getOrCreate(0, 2);
        final Cell cellC3 = spreadsheet.getOrCreate(2, 2);
        final Cell cellA1 = spreadsheet.getOrCreate(0, 0);
        final double valueC1 = cellC1.eval().asNumber();
        final double valueC3 = cellC3.eval().asNumber();
        final double valueA1 = cellA1.eval().asNumber();
        assertEquals(5.0, valueC1, 0.0);
        assertEquals(8.0, valueC3, 0.0);
        assertEquals(13.0, valueA1, 0.0);
        interpreter.parseAndExecute("CLEAR C3", spreadsheet);
        final Cell cell2C3 = spreadsheet.getOrCreate(2, 2);
        final Cell cell2A1 = spreadsheet.getOrCreate(0, 0);
        final double value2C3 = cell2C3.eval().asNumber();
        final double value2A1 = cell2A1.eval().asNumber();
        assertEquals(0.0, value2C3, 0.0);
        assertEquals(5.0, value2A1, 0.0);
    }
    
    @Test
    public void testSetHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandSetFactory command = new TuiCommandSetFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "Set the value of a cell";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("SET");
        String expectedLongMessage = "SET CellReference Formula: to set the content of a cell.\n" 
                                     + "Ex: SET A1 hello, to set the string \"hello\" as content of cell A1.\n"
                                     + "Ex: SET A1 = A2 + 3, to set A1 as the sum of the content of A1 plus 3.";
        assertEquals(expectedLongMessage, longErrorMessage);
        //GetName
        final Command set = command.getCommand("SET A1 9", spreadsheet);
        assertEquals("Set", set.getName());
    }
    
    @Test
    public void testClearHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandClearFactory command = new TuiCommandClearFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "Clear the spreadsheet or a cell";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("CLEAR");
        String expectedLongMessage = "CLEAR: clear the entire spreadsheet.\n"
                                     + "CLEAR reference: clear the reference cell content.";
        assertEquals(expectedLongMessage, longErrorMessage);
        //GetName
        final Command clear = command.getCommand("CLEAR", spreadsheet);
        assertEquals("Clear", clear.getName());
    }
    
    @Test
    public void HelpSaveTest() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandSaveFactory command = new TuiCommandSaveFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "Save the spreadsheet ";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("SAVE");
        String expectedLongMessage = "SAVE FILE-PATH: save the spreadsheet as csv into the given directory.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    public void testOpenHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandOpenFactory command = new TuiCommandOpenFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "Open a spreadsheet ";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("OPEN");
        String expectedLongMessage = "OPEN FILE-PATH: open the spreadsheet from a csv file.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    @Test
    public void testExitHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandExitFactory command = new TuiCommandExitFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "quit Cellarium";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("EXIT");
        String expectedLongMessage = "EXIT: to quit the Cellarium spreadheet.";
        assertEquals(expectedLongMessage, longErrorMessage);
        //GetName
        final Command exit = command.getCommand("EXIT", spreadsheet);
        assertEquals("Exit", exit.getName());
    }
    
    @Test
    public void testHelpHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandInterpreter interpreter = new TuiCommandInterpreter();
        final TuiCommandHelpFactory command = new TuiCommandHelpFactory(interpreter);
        String errorMessage = command.helpShort();
        String expectedMessage = "print the list of commands or their detailed help";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("HELP");
        String expectedLongMessage = "HELP: print all the commands and a little descriction for each of them.\n"
                                      + "HELP command: print a detailed description of the command.";
        assertEquals(expectedLongMessage, longErrorMessage);
        //GetName
        final Command help = command.getCommand("HELP", spreadsheet);
        assertEquals("Help", help.getName());
    }
    
    @Test
    public void testPrintHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandPrintFactory command = new TuiCommandPrintFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "Print the spreadsheet or a cell";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("PRINT");
        String expectedLongMessage = "PRINT: print the entire spreadsheet.\n"
                                      + "PRINT reference: print the reference cell content.";
        assertEquals(expectedLongMessage, longErrorMessage);
        //GetName
        final Command print = command.getCommand("PRINT", spreadsheet);
        assertEquals("Print", print.getName());
    }
      
    @Test
    public void testHistoryHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandInterpreter interpreter = new TuiCommandInterpreter();
        final TuiCommandHistoryFactory command = new TuiCommandHistoryFactory(interpreter);
        String errorMessage = command.helpShort();
        String expectedMessage = "print the command history list.";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("HISTORY");
        String expectedLongMessage = "HISTORY: print all the commands executed in the past and the ones that can be reexecuted.";
        assertEquals(expectedLongMessage, longErrorMessage);
        //GetName
        final Command history = command.getCommand("HISTORY", spreadsheet);
        assertEquals("History", history.getName());
    }

    @Test
    public void testExportHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandExportFactory command = new TuiCommandExportFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "Export the spreadsheet ";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("EXPORT");
        String expectedLongMessage = "EXPORT FILE-PATH: export the spreadsheet in csv into the given directory.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    @Test
    public void testImportHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandImportFactory command = new TuiCommandImportFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "Import a spreadsheet ";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("IMPORT");
        String expectedLongMessage = "IMPORT FILE-PATH: import the spreadsheet from a csv file.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    @Test
    public void testUndoHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandUndoFactory command = new TuiCommandUndoFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "undo last command.";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("UNDO");
        String expectedLongMessage = "UNDO: to undo last command (if present).";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    @Test
    public void testRedoHelp() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final TuiCommandRedoFactory command = new TuiCommandRedoFactory();
        String errorMessage = command.helpShort();
        String expectedMessage = "redo last command.";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = command.helpLong("REDO");
        String expectedLongMessage = "REDO: to redo last command (if present).";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
}
