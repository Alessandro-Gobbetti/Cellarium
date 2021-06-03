package tui;

import commands.Command;
import io.CommandSaveOrExport;
import spreadsheet.Spreadsheet;

/**
 * To create a Save command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandSaveOrExportFactory implements TuiCommandFactory {

    private boolean isSave;
    
    /**
     * Creator for TuiCommandSaveOrExportFactory.
     * @param isSave true to create a save command, false to create a export command
     */
    public TuiCommandSaveOrExportFactory(final boolean isSave) {
        this.isSave = isSave;
    }
    
    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new CommandSaveOrExport(isSave, input, spreadsheet);
    }
    
    @Override
    public String helpShort() {
        return isSave ? "Save the spreadsheet " : "Export the spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: " 
            + (isSave 
            ? "save the spreadsheet as a cellarium file into the given directory."
            : "export the spreadsheet in csv into the given directory.");
    }
}
