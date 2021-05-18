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

    SpreadsheetCommandInterpreter interpreter;
    
    public SpreadsheetCommandHelp(final SpreadsheetCommandInterpreter interpreter) {
        this.interpreter = interpreter;
    }
    
    public boolean parseAndExecute(String sourceCode, Spreadsheet spreadsheet) {
        //remove spaces at the beginning or at the end
        String trimmedSourceCode = sourceCode.trim();
        if (trimmedSourceCode == "") {
            interpreter.helpCommandList();
        } else {
            interpreter.helpCommand(trimmedSourceCode);
        }
        return true;
    }
    
    public String helpShort() {
        return "print the list of commands or their detailed help";
    }
    
    public String helpLong(final String commandName) {
        return commandName + ": print all the commands and a little descriction for each of them.\n"
               + commandName + " command: print a detailed description of the command.";
    }
}
