package gui;

import commands.Command;
import io.CommandSaveOrExport;

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
        return new CommandSaveOrExport(false, input, spreadsheetView.getSpreadsheet());
    }
}
