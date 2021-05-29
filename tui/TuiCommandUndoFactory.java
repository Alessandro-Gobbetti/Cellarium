package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandUndoFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(String input, Spreadsheet spreadsheet) {
        return new TuiCommandUndo(input, spreadsheet);
    }
    
    @Override
    public String helpShort() {
        return "undo last command.";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": to undo last command (if present).";
    }
}
