package gui;

import commands.Command;

/**
 * Write a description of class GuiCommandFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract interface GuiCommandFactory {
    
    /**
     * Parse and execute a command on a given spreadsheetmodel.
     * 
     * @param spreadsheet the spreadsheet on which to execute the commands.
     * @param input the command and parameters to parse and execute
     * @return true if there are no errors during parsing and execution, otherwise return false.
     */
    public abstract Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView);
    
    /**
     * Return a short description for the command.
     * @return a short description for command.
     */
    public abstract String helpShort();
    
    /**
     * Return a long description for the command.
     * @param commandName  the name of the command.
     * @return a long description for command.
     */
    public abstract String helpLong(final String commandName);

}
