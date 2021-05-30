package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandPrintFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(String input, Spreadsheet spreadsheet) {
        return new TuiCommandPrint(input, spreadsheet);
    }
    
    @Override
    public String helpShort() {
        return "Print the spreadsheet or a cell";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": print the entire spreadsheet.\n"
               + commandName + " reference: print the reference cell content.";
    }
}