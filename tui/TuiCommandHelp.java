package tui;

import commands.NotUndoableNotStateChangingCommand;
import spreadsheet.Spreadsheet;

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

    private SpreadsheetCommandInterpreter interpreter;
    private String sourceCode;
    private Spreadsheet spreadsheet;
    
    /**
     * Constructor for SpreadsheetCommandHelp.
     * @param interpreter the interpreter
     */
    public TuiCommandHelp(final SpreadsheetCommandInterpreter interpreter,
                          final String sourceCode,
                          final Spreadsheet spreadsheet) {
        super();
        this.interpreter = interpreter;
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
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
