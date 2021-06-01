package gui;

import commands.Command;
import commands.CommandUndoRedo;

/**
 * Write a description of class guiCommandSetFactory here.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandUndoFactory implements GuiCommandFactory {

    @Override
    public Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView) {
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