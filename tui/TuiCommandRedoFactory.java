package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandRedoFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new TuiCommandRedo();
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
