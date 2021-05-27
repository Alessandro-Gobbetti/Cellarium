package tui;

import spreadsheet.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SpreadsheetCommandInterpreterTest.
 *
 * @author  Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetCommandInterpreterTest {
    @Test
    public void Set1Test() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandInterpreter interpreter = new SpreadsheetCommandInterpreter();
        interpreter.parseAndExecute("SET C1 5", spreadsheet);
        final int col = CellReference.fromAlpha26("C");
        final Cell cell = spreadsheet.getOrCreate(0, col);
        final double value = cell.eval().asNumber();
        assertEquals(5.0, value, 0.0);
    }
    
    @Test
    public void Set2Test() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandInterpreter interpreter = new SpreadsheetCommandInterpreter();
        interpreter.parseAndExecute("SET C1 5", spreadsheet);
        interpreter.parseAndExecute("SET C3 8", spreadsheet);
        interpreter.parseAndExecute("SET A1 = C1 + C3", spreadsheet);
        final int colC = CellReference.fromAlpha26("C");
        final int colA = CellReference.fromAlpha26("A");
        final Cell cellC1 = spreadsheet.getOrCreate(0, colC);
        final Cell cellC3 = spreadsheet.getOrCreate(2, colC);
        final Cell cellA1 = spreadsheet.getOrCreate(0, colA);
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
        final SpreadsheetCommandInterpreter interpreter = new SpreadsheetCommandInterpreter();
        interpreter.parseAndExecute("SET C1 5", spreadsheet);
        final int col = CellReference.fromAlpha26("C");
        final Cell cell1 = spreadsheet.getOrCreate(0, col);
        final double value1 = cell1.eval().asNumber();
        assertEquals(5.0, value1, 0.0);
        interpreter.parseAndExecute("CLEAR C1", spreadsheet);
        final Cell cell2 = spreadsheet.getOrCreate(0, col);
        final double value2 = cell2.eval().asNumber();
        assertEquals(0.0, value2, 0.0);
    }
    
    
    @Test
    public void Clear2Test() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandInterpreter interpreter = new SpreadsheetCommandInterpreter();
        interpreter.parseAndExecute("SET C1 5", spreadsheet);
        interpreter.parseAndExecute("SET C3 8", spreadsheet);
        interpreter.parseAndExecute("SET A1 = C1 + C3", spreadsheet);
        final int colC = CellReference.fromAlpha26("C");
        final int colA = CellReference.fromAlpha26("A");
        final Cell cellC1 = spreadsheet.getOrCreate(0, colC);
        final Cell cellC3 = spreadsheet.getOrCreate(2, colC);
        final Cell cellA1 = spreadsheet.getOrCreate(0, colA);
        final double valueC1 = cellC1.eval().asNumber();
        final double valueC3 = cellC3.eval().asNumber();
        final double valueA1 = cellA1.eval().asNumber();
        assertEquals(5.0, valueC1, 0.0);
        assertEquals(8.0, valueC3, 0.0);
        assertEquals(13.0, valueA1, 0.0);
        interpreter.parseAndExecute("CLEAR C3", spreadsheet);
        final Cell cell2C3 = spreadsheet.getOrCreate(2, colC);
        final Cell cell2A1 = spreadsheet.getOrCreate(0, colA);
        final double value2C3 = cell2C3.eval().asNumber();
        final double value2A1 = cell2A1.eval().asNumber();
        assertEquals(0.0, value2C3, 0.0);
        //assertEquals(5.0, value2A1, 0.0);
    }
    
    @Test
    public void HelpSetTest() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandSet setInterpreter = new SpreadsheetCommandSet();
        String errorMessage = setInterpreter.helpShort();
        String expectedMessage = "Set the value of a cell";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = setInterpreter.helpLong("SET");
        String expectedLongMessage = "SET CellReference Formula: to set the content of a cell.\n" 
                                     + "Ex: SET A1 hello, to set the string \"hello\" as content of cell A1.\n"
                                     + "Ex: SET A1 = A2 + 3, to set A1 as the sum of the content of A1 plus 3.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    @Test
    public void HelpClearTest() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandClear clearInterpreter = new SpreadsheetCommandClear();
        String errorMessage = clearInterpreter.helpShort();
        String expectedMessage = "Clear the spreadsheet or a cell";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = clearInterpreter.helpLong("CLEAR");
        String expectedLongMessage = "CLEAR: clear the entire spreadsheet.\n"
                                     + "CLEAR reference: clear the reference cell content.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    @Test
    public void HelpSaveTest() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandSave saveInterpreter = new SpreadsheetCommandSave();
        String errorMessage = saveInterpreter.helpShort();
        String expectedMessage = "Save the spreadsheet ";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = saveInterpreter.helpLong("SAVE");
        String expectedLongMessage = "SAVE FILE-PATH: save the spreadsheet as csv into the given directory.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    @Test
    public void HelpExitTest() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandExit exitInterpreter = new SpreadsheetCommandExit();
        String errorMessage = exitInterpreter.helpShort();
        String expectedMessage = "quit Cellarium";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = exitInterpreter.helpLong("EXIT");
        String expectedLongMessage = "EXIT: to quit the Cellarium spreadheet.";
        assertEquals(expectedLongMessage, longErrorMessage);
        final boolean value = exitInterpreter.parseAndExecute("", spreadsheet);
        assertTrue(value);
    }
    
    @Test
    public void HelpHelpTest() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandInterpreter interpreter = new SpreadsheetCommandInterpreter();
        final SpreadsheetCommandHelp helpInterpreter = new SpreadsheetCommandHelp(interpreter);
        String errorMessage = helpInterpreter.helpShort();
        String expectedMessage = "print the list of commands or their detailed help";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = helpInterpreter.helpLong("HELP");
        String expectedLongMessage = "HELP: print all the commands and a little descriction for each of them.\n"
                                      + "HELP command: print a detailed description of the command.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    @Test
    public void HelpOpenTest() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandOpen openInterpreter = new SpreadsheetCommandOpen();
        String errorMessage = openInterpreter.helpShort();
        String expectedMessage = "Open a spreadsheet ";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = openInterpreter.helpLong("OPEN");
        String expectedLongMessage = "OPEN FILE-PATH: open the spreadsheet from a csv file.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    @Test
    public void HelpPrintTest() {
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandPrint printInterpreter = new SpreadsheetCommandPrint();
        String errorMessage = printInterpreter.helpShort();
        String expectedMessage = "Print the spreadsheet or a cell";
        assertEquals(expectedMessage, errorMessage);
        String longErrorMessage = printInterpreter.helpLong("PRINT");
        String expectedLongMessage = "PRINT: print the entire spreadsheet.\n"
                                      + "PRINT reference: print the reference cell content.";
        assertEquals(expectedLongMessage, longErrorMessage);
    }
    
    


}
