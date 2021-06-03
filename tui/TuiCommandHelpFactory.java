package tui;

import commands.Command;
import spreadsheet.Spreadsheet;

/**
 * This is a factory for tui commands.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandHelpFactory implements TuiCommandFactory {

    private TuiCommandInterpreter interpreter;

    /**
     * Creator for TuiCommandHelpFactory.
     * 
     * @param interpreter the interpreter
     */
    public TuiCommandHelpFactory(final TuiCommandInterpreter interpreter) {
        this.interpreter = interpreter;
    }
    
    @Override
    public Command getCommand(final String input, final Spreadsheet spreadsheet) {
        return new TuiCommandHelp(interpreter, input);
    }
    
    @Override
    public String helpShort() {
        return "print the list of commands or their detailed help";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": print all the commands and a little descriction for each of them.\n"
               + commandName + " command: print a detailed description of the command.";
    }
}
