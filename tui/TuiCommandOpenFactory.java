package tui;

import commands.Command;
import io.CommandOpenOrImport;
import spreadsheet.Spreadsheet;

/**
 * To create an Open command.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandOpenFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new CommandOpenOrImport(true, input, spreadsheet);
    }
    
    @Override
    public String helpShort() {
        return "Open a spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: open the spreadsheet from a csv file.";
    }
}
