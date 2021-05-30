package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandHistoryFactory implements TuiCommandFactory {

    private TuiCommandInterpreter interpreter;

    /**
     * Creator for TuiCommandHistoryFactory.
     * 
     * @param interpreter the interpreter
     */
    public TuiCommandHistoryFactory(final TuiCommandInterpreter interpreter) {
        this.interpreter = interpreter;
    }
    
    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new TuiCommandHistory(interpreter, input);
    }
    
    @Override
    public String helpShort() {
        return "print the command history list.";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": print all the commands executed in the past and the ones that can be reexecuted.";
    }
}
