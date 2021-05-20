import parser.TokenType;

/**
 * Write a description of class SpreadsheetSet here.
 * 
 * </p>
 * CLEAR         to clear all the spreadsheet
 * CLEAR A1      to clear a cell only
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetCommandClear implements SpreadsheetCommand {

    @Override
    public boolean parseAndExecute(final String sourceCode, final Spreadsheet spreadsheet) {
        final CellariumParser parser = new CellariumParser();
        parser.initLexer(sourceCode);
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            spreadsheet.clear();
        } else {
            final Node node = parser.parseCellReference();
            if (!(node instanceof CellReference)) {
                return false;
            }
            final CellReference cellReference = (CellReference) node;
            if (!parser.currentTokenMatches(TokenType.END_OF_FILE)) {
                return false;
            }
            spreadsheet.remove(cellReference.getRow(0), cellReference.getCol(0));
        }
        return true;
    }
    
    @Override
    public String helpShort() {
        return "Clear the spreadsheet or a cell";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": clear the entire spreadsheet.\n"
        + commandName + " reference: clear the reference cell content.";
    }
}
