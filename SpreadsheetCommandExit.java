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
public class SpreadsheetCommandExit implements SpreadsheetCommand{

    @Override
    public boolean parseAndExecute(String sourceCode, Spreadsheet spreadsheet) {
        CellariumTextUserInterface.setTerminated(true);
        return true;
    }

    @Override
    public String helpShort() {
        return "quit Cellarium";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": to quit the Cellarium spreadheet.";
    }
}
