package tui;

import commands.NotUndoableStateChangingCommand;
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
public class TuiCommandExit extends NotUndoableStateChangingCommand {
    
    private String sourceCode;
    private Spreadsheet spreadsheet;
    

    /**
     * Creator for TuiCommandOpen.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
     */
    public TuiCommandExit(final String sourceCode, final Spreadsheet spreadsheet) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public String getName() {
        return "Open";
    }
    
    @Override
    public void doit() {
        CellariumTui.setTerminated(true);
        setLastOperationOk();
    }
    
}
