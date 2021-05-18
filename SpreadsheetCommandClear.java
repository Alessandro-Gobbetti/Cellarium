import parser.*;

/**
 * Write a description of class SpreadsheetSet here.
 * 
 * CLEAR         to clear all the spreadsheet
 * CLEAR A1      to clear a cell only
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetCommandClear implements SpreadsheetCommand{

    public boolean parseAndExecute(String sourceCode, Spreadsheet spreadsheet) {
        final CellariumParser parser = new CellariumParser();
        parser.initLexer(sourceCode);
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            spreadsheet.clear();
        } else {
            Node node = parser.parseCellReference();
            if (!(node instanceof CellReference)) {
                return false;
            }
            CellReference cellReference = (CellReference) node;
            if (!parser.currentTokenMatches(TokenType.END_OF_FILE)) {
                return false;
            }
            spreadsheet.remove(cellReference.getRow(0), cellReference.getCol(0));
            //Cell cell = spreadsheet.getOrCreate(cellReference.getRow(0), cellReference.getCol(0));
            //System.out.println(cell.eval().asString());
        }
        return true;
    }
    
    public String helpShort() {
        return "Clear the spreadsheet or a cell";
    }
    
    public String helpLong(final String commandName) {
        return (commandName + ": clear the entire spreadsheet.\n" +
                commandName + " reference: clear the reference cell content.");
        }
}
