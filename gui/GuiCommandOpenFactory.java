package gui;

import commands.Command;

/**
 * Write a description of class guiCommandSetFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandOpenFactory implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandOpen(input, spreadsheetView);
    }
    
    @Override
    public String helpShort() {
        return "Open a spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: open the spreadsheet from a csv file.";
    }
}
