package tui;

import commands.CommandUndoRedo;
import spreadsheet.Spreadsheet;

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
public class TuiCommandUndo extends CommandUndoRedo {
    
    private String sourceCode;
    private Spreadsheet spreadsheet;

    /**
     * Creator for TuiCommandOpen.
     */
    public TuiCommandUndo(final String sourceCode, final Spreadsheet spreadsheet) {
        super(true);
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
}
