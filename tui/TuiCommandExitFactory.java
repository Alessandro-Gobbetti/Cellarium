package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandExitFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new TuiCommandExit(input, spreadsheet);
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
