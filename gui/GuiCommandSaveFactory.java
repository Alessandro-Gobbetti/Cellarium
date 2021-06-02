package gui;

import commands.Command;

/**
 * To create a Save command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandSaveFactory implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input,
                              final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandSaveOrExport(true, input, spreadsheetView);
    }
    
}
