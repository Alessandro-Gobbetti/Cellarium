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
     */
    public GuiCommandUndo() {
        super(true);
    }
}
