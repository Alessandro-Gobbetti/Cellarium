package gui;

import commands.CommandUndoRedo;

/**
 * An Undo Command.
 *
 * @author Alessandro-Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandUndo extends CommandUndoRedo {
    
    /**
     * Creator for GuiCommandUndo.
     * 
     * @param sourceCode the command to parse
     * @param spreadsheetView the spreadsheet model
     */
    public GuiCommandUndo(final String sourceCode,
                          final SpreadsheetViewTableModel spreadsheetView) {
        super(true);
    }
}
