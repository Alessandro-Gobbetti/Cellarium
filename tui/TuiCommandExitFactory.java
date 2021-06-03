package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * To create an Exit command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandExitFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new TuiCommandExit();
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
