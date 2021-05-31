package tui;

import commands.CommandUndoRedo;

/**
 * To quit the program.
 * 
 * <p>
 * EXIT             to quit the program.
 * </p>
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandRedo extends CommandUndoRedo {

    /**
     * Creator for TuiCommandOpen.
     */
    public TuiCommandRedo() {
        super(false);
    }
    
}
