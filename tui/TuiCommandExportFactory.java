package tui;

import commands.Command;
import io.CommandSaveOrExport;
import spreadsheet.Spreadsheet;

/**
 * To create an Export command.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandExportFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new CommandSaveOrExport(false, input, spreadsheet);
    }
    
    @Override
    public String helpShort() {
        return "Export the spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: export the spreadsheet in csv into the given directory.";
    }
}
