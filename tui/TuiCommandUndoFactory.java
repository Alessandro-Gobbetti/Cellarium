package tui;

import commands.Command;
import commands.CommandUndoRedo;
import spreadsheet.Spreadsheet;

/**
 * To create a Undo command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandUndoFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new CommandUndoRedo(true);
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
