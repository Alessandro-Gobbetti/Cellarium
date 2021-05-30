package gui;

import commands.Command;

/**
 * Write a description of class guiCommandSetFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandClearFactory  implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandClear(input, spreadsheetView);
    }
    
    @Override
    public String helpShort() {
        return "Clear the spreadsheet or a cell";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": clear the entire spreadsheet.\n"
               + commandName + " reference: clear the reference cell content.";
    }
}
