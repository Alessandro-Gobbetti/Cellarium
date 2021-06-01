package gui;

import commands.Command;
import commands.CommandUndoRedo;

/**
 * Write a description of class guiCommandSetFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandRedoFactory implements GuiCommandFactory {

    @Override
    public Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView) {
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
