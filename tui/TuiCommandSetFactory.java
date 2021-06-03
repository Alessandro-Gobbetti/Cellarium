package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * To create a set command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandSetFactory implements TuiCommandFactory {

    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new TuiCommandSet(input, spreadsheet);
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
