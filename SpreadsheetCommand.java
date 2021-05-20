
/**
 * Interface for Spreadsheet commands.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public abstract interface SpreadsheetCommand {

    public abstract boolean parseAndExecute(String input, Spreadsheet spreadsheet);
    
    public abstract String helpShort();
    
    public abstract String helpLong(final String commandName);
}
