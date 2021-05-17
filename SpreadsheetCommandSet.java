
/**
 * Write a description of class SpreadsheetSet here.
 * 
 * SET A1 23
 * SET CELLREFERENCE CELL
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetCommandSet extends SpreadsheetCommand{

    public boolean parseAndExecute(String sourceCode, Spreadsheet spreadsheet) {
        final CellariumParser parser = new CellariumParser();
        parser.initLexer(sourceCode);
        Node node = parser.parseCellReference();
        if (!(node instanceof CellReference)) {
            return false;
        }
        CellReference cellReference = (CellReference) node;
        Node content = parser.parseCell();
        if (content.isError()) {
            System.out.println(content.toString());
            return false;
        }
        Cell cell = spreadsheet.getOrCreate(cellReference.getRow(0), cellReference.getCol(0));
        cell.setFormula(content);
        return true;
    }
    
    public String helpShort() {
        return "Set the value of a cell";
    }
    
    public String helpLong(final String commandName) {
        return "FIXME: examlpes... set formula...";
    }
}
