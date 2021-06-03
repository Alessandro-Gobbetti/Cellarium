package gui;

import commands.Command;
import io.CommandSaveOrExport;

/**
 * To create a Save command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandSaveOrExportFactory implements GuiCommandFactory {

    private boolean isSave;
    
    /**
     * Constructor for GuiCommandSaveOrExportFactory.
     * @param isSave true to create a save command, false for an export command.
     */
    public GuiCommandSaveOrExportFactory(final boolean isSave) {
        this.isSave = isSave;
    }
    
    @Override
    public Command getCommand(final String input,
                              final SpreadsheetViewTableModel spreadsheetView) {
        return new CommandSaveOrExport(isSave, input, spreadsheetView.getSpreadsheet());
    }
    
}
