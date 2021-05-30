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
     * Creator for TuiCommandOpen.
     */
    public GuiCommandRedo(final String sourceCode, final SpreadsheetViewTableModel spreadsheetView) {
        super(false);
    }
}
