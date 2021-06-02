package gui;

import commands.Command;
import commands.CommandUndoRedo;

/**
 * To create a Redo command.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandRedoFactory implements GuiCommandFactory {

    @Override
    public Command getCommand(final String input,
                              final SpreadsheetViewTableModel spreadsheetView) {
        return new CommandUndoRedo(false);
    }
    
}
