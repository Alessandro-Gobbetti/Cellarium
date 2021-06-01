package tui;

import commands.NotUndoableStateChangingCommand;

/**
 * To quit the program.
 * 
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandExit extends NotUndoableStateChangingCommand {
    
    @Override
    public String getName() {
        return "Exit";
    }
    
    @Override
    public void doit() {
        Main.setTerminated(true);
        setLastOperationOk();
    }
    
}
