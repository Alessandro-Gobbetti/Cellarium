package tui;

import commands.NotUndoableNotStateChangingCommand;

/**
 * To print all the history commands.
 * 
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandHistory extends NotUndoableNotStateChangingCommand {

    private TuiCommandInterpreter interpreter;
    
    /**
     * Constructor for TuiCommandHistory.
     * 
     * @param interpreter the interpreter
     */
    public TuiCommandHistory(final TuiCommandInterpreter interpreter) {
        super();
        this.interpreter = interpreter;
    }
    
    @Override
    public String getName() {
        return "History";
    }
    
    @Override
    public void doit() {
        interpreter.printCommandHistory();
        setLastOperationOk();
    }
    
}
