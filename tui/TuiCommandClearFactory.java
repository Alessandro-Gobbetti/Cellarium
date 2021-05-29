package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandClearFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(String input, Spreadsheet spreadsheet) {
        return new TuiCommandClear(input, spreadsheet);
    }
    
    @Override
    public String helpShort() {
        return "Clear the spreadsheet or a cell";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": clear the entire spreadsheet.\n"
               + commandName + " reference: clear the reference cell content.";
    }
}
