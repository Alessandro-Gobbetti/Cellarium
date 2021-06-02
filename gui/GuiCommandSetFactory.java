package gui;

import commands.Command;

/**
 * To create a Set command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandSetFactory  implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input,
                              final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandSet(input, spreadsheetView);
    }
    
}
