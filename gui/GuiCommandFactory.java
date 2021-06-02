package gui;

import commands.Command;

/**
 * To create gui commands.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public abstract interface GuiCommandFactory {
    
    /**
     * Parse and execute a command on a given spreadsheetmodel.
     * 
     * @param spreadsheetView the view table model on which to execute the commands.
     * @param input the command and parameters to parse and execute
     * @return true if there are no errors during parsing and execution, otherwise return false.
     */
    public abstract Command getCommand(final String input,
                                       final SpreadsheetViewTableModel spreadsheetView);
    
}
