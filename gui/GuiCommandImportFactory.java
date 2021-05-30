package gui;

import commands.Command;

/**
 * Write a description of class guiCommandSetFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandImportFactory implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandImport(input, spreadsheetView);
    }
    
    @Override
    public String helpShort() {
        return "Import a spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: import the spreadsheet from a csv file.";
    }
}
