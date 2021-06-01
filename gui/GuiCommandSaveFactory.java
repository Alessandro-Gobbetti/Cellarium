package gui;

import commands.Command;

/**
 * Write a description of class GuiCommandSaveFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandSaveFactory implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandSaveOrExport(true, input, spreadsheetView);
    }
    
    @Override
    public String helpShort() {
        return "Save the spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: save the spreadsheet as csv into the given directory.";
    }
}
