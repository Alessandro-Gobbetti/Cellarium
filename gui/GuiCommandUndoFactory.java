package gui;

import commands.Command;

/**
 * Write a description of class guiCommandSetFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandUndoFactory implements GuiCommandFactory {

    @Override
    public Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandUndo();
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