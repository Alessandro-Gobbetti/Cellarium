package gui;

import commands.Command;

/**
 * Write a description of class guiCommandSetFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandExportFactory implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandExport(input, spreadsheetView);
    }
    
    @Override
    public String helpShort() {
        return "Export the spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: export the spreadsheet in csv into the given directory.";
    }
}
