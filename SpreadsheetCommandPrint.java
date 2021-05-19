import parser.*;

/**
 * Write a description of class SpreadsheetSet here.
 * 
 * PRINT         to print all the spreadsheet
 * PRINT A1      to print a cell only
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetCommandPrint implements SpreadsheetCommand{

    @Override
    public boolean parseAndExecute(final String sourceCode, final Spreadsheet spreadsheet) {
        final CellariumParser parser = new CellariumParser();
        parser.initLexer(sourceCode);
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            spreadsheet.print();
        } else {
            final Node node = parser.parseCellReference();
            if (!(node instanceof CellReference)) {
                return false;
            }
            final CellReference cellReference = (CellReference) node;
            if (!parser.currentTokenMatches(TokenType.END_OF_FILE)) {
                return false;
            }
            final Cell cell = spreadsheet.getOrCreate(cellReference.getRow(0), cellReference.getCol(0));
            System.out.println(cell.eval().asString());
        }
        return true;
    }
    
    @Override
    public String helpShort() {
        return "Print the spreadsheet or a cell";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + ": print the entire spreadsheet.\n" +
                commandName + " reference: print the reference cell content.";
    }
}
