package tui;

import commands.Command;
import io.CommandOpenOrImport;
import spreadsheet.Spreadsheet;

/**
 * To create an Open command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandOpenOrImportFactory implements TuiCommandFactory {

    private boolean isOpen;
    
    /**
     * Constructor for TuiCommandOpenOrImportFactory.
     * @param isOpen true to create an open command, false for a import command.
     */
    public TuiCommandOpenOrImportFactory(final boolean isOpen) {
        this.isOpen = isOpen;
    }
    
    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new CommandOpenOrImport(isOpen, input, spreadsheet);
    }
    
    @Override
    public String helpShort() {
        return isOpen ? "Open a spreadsheet " : "Import a spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return isOpen 
            ? commandName + " FILE-PATH: open the spreadsheet from a cellarium file."
            : commandName + " FILE-PATH: import the spreadsheet from a csv file.";
    }
}
