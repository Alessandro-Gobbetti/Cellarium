package tui;

import commands.Command;
import commands.CommandUndoRedo;
import spreadsheet.Spreadsheet;

/**
 * To create a Redo command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandRedoFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new CommandUndoRedo(false);
    }
    
    @Override
    public String helpShort() {
        return "redo last command.";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": to redo last command (if present).";
    }
}
