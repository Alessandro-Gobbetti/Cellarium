package gui;

import commands.Command;

/**
 * To create an Open command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandOpenFactory implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input,
                              final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandOpenOrImport(true, input, spreadsheetView);
    }

}
