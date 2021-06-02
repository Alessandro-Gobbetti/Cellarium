package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * To create an Open command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandImportFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new TuiCommandOpenOrImport(false, input, spreadsheet);
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
