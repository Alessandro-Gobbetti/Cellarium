package tui;

import commands.NotUndoableNotStateChangingCommand;

/**
 * To print a help message for all commands or just for one.
 * 
 * <p>
 * HELP             to print the list of commands
 * HELP COMMAND     to print the help of a given command
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandHelp extends NotUndoableNotStateChangingCommand {

    private TuiCommandInterpreter interpreter;
    private String sourceCode;
    
    /**
     * Constructor for SpreadsheetCommandHelp.
     * @param interpreter the interpreter
     * @param sourceCode  the sourceCode 
     */
    public TuiCommandHelp(final TuiCommandInterpreter interpreter,
                          final String sourceCode) {
        super();
        this.interpreter = interpreter;
        this.sourceCode = sourceCode;
    }
    
    @Override
    public String getName() {
        return "Help";
    }
    
    @Override
    public void doit() {
        //remove spaces at the beginning or at the end
        final String trimmedSourceCode = sourceCode.trim();
        if ("".equals(trimmedSourceCode)) {
            interpreter.helpCommandList();
        } else {
            interpreter.helpCommand(trimmedSourceCode);
        }
        setLastOperationOk();
    }
    
}
