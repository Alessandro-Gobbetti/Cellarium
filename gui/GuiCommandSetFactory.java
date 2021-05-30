package gui;

import commands.Command;

/**
 * Write a description of class guiCommandSetFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandSetFactory  implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input, final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandSet(input, spreadsheetView);
    }
    
    @Override
    public String helpShort() {
        return "Set the value of a cell";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " CellReference Formula: to set the content of a cell.\n"
               + "Ex: SET A1 hello, to set the string \"hello\" as content of cell A1.\n"
               + "Ex: SET A1 = A2 + 3, to set A1 as the sum of the content of A1 plus 3.";
    }
}
