package gui;

import commands.CommandUndoRedo;

/**
 * Write a description of class guiCommandSet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandRedo extends CommandUndoRedo {
    
    /**
     * Creator for GuiCommandRedo.
     * 
     * @param sourceCode the command to parse
     * @param spreadsheetView the spreadsheet model
     */
    public GuiCommandRedo(final String sourceCode,
                          final SpreadsheetViewTableModel spreadsheetView) {
        super(false);
    }
}
