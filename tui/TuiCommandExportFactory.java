package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandExportFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(String input, Spreadsheet spreadsheet) {
        return new TuiCommandExport(input, spreadsheet);
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
