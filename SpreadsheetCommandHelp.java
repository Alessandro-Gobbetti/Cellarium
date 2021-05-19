import parser.*;

/**
 * Write a description of class SpreadsheetSet here.
 * 
 * HELP             to print the list of commands
 * HELP COMMAND     to print the help of a given command
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetCommandHelp implements SpreadsheetCommand{

    private SpreadsheetCommandInterpreter interpreter;
    
    public SpreadsheetCommandHelp(final SpreadsheetCommandInterpreter interpreter) {
        super();
        this.interpreter = interpreter;
    }
    
    @Override
    public boolean parseAndExecute(final String sourceCode, final Spreadsheet spreadsheet) {
        //remove spaces at the beginning or at the end
        final String trimmedSourceCode = sourceCode.trim();
        if (trimmedSourceCode == "") {
            interpreter.helpCommandList();
        } else {
            interpreter.helpCommand(trimmedSourceCode);
        }
        return true;
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
