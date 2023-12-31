package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * To create a clear command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandClearFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
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
