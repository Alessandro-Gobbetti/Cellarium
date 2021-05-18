
/**
 * Write a description of class SpreadsheetCommand here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract interface SpreadsheetCommand {

    public abstract boolean parseAndExecute(String input, Spreadsheet spreadsheet);
    
    public abstract String helpShort();
    
    public abstract String helpLong(final String commandName);
}
