package tui;

import spreadsheet.*;

/**
 * To quit the program.
 * 
 * <p>
 * EXIT             to quit the program.
 * </p>
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetCommandExit implements SpreadsheetCommand {

    @Override
    public boolean parseAndExecute(final String sourceCode, final Spreadsheet spreadsheet) {
        CellariumTui.setTerminated(true);
        return true;
    }

    @Override
    public String helpShort() {
        return "quit Cellarium";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": to quit the Cellarium spreadheet.";
    }
}
