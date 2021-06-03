package gui;

import commands.Command;
import io.CommandOpenOrImport;

/**
 * To create an Open command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandOpenOrImportFactory implements GuiCommandFactory {

    private boolean isOpen;
    
    /**
     * Constructor for GuiCommandOpenOrImportFactory.
     * @param isOpen true to create an open command, false for an export command.
     */
    public GuiCommandOpenOrImportFactory(final boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public Command getCommand(final String input,
                              final SpreadsheetViewTableModel spreadsheetView) {
        return new CommandOpenOrImport(isOpen, input, spreadsheetView.getSpreadsheet());
    }

}
