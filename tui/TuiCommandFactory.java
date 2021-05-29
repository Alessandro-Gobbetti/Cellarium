package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public abstract interface TuiCommandFactory {

    /**
     * Parse and execute a command on a given spreadsheet.
     * 
     * @param spreadsheet the spreadsheet on which to execute the commands.
     * @param input the command and parameters to parse and execute
     * @return true if there are no errors during parsing and execution, otherwise return false.
     */
    public abstract Command getCommand(String input, Spreadsheet spreadsheet);
    
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
