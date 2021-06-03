package tui;

import commands.Command;
import io.CommandSaveOrExport;
import spreadsheet.Spreadsheet;

/**
 * To create a Save command.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandSaveFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new CommandSaveOrExport(true, input, spreadsheet);
    }
    
    @Override
    public String helpShort() {
        return "Save the spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: save the spreadsheet as csv into the given directory.";
    }
}
