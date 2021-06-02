package gui;

import commands.Command;

/**
 * To create an Export command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandExportFactory implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input,
                              final SpreadsheetViewTableModel spreadsheetView) {
        return new GuiCommandSaveOrExport(false, input, spreadsheetView);
    }
}
