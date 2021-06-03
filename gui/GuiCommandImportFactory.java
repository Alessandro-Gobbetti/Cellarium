package gui;

import commands.Command;
import io.CommandOpenOrImport;

/**
 * To create a Import command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandImportFactory implements GuiCommandFactory {


    @Override
    public Command getCommand(final String input,
                              final SpreadsheetViewTableModel spreadsheetView) {
        return new CommandOpenOrImport(false, input, spreadsheetView.getSpreadsheet());
    }
    
}
