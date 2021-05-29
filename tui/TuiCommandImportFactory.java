package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandImportFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(String input, Spreadsheet spreadsheet) {
        return new TuiCommandImport(input, spreadsheet);
    }
    
    @Override
    public String helpShort() {
        return "Import a spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: import the spreadsheet from a csv file.";
    }
}
