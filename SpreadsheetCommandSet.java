
/**
 * Write a description of class SpreadsheetSet here.
 * 
 * SET A1 23
 * SET CELLREFERENCE CELL
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetCommandSet implements SpreadsheetCommand{

    public boolean parseAndExecute(String sourceCode, Spreadsheet spreadsheet) {
        final CellariumParser parser = new CellariumParser();
        parser.initLexer(sourceCode);
        // parse the cell referernce
        Node node = parser.parseCellReference();
        if (!(node instanceof CellReference)) {
            return false;
        }
        CellReference cellReference = (CellReference) node;
        // parse the new content of the cell
        Node content = parser.parseCell();
        
        if (content instanceof Text) {
            // remove cell reference from text to print in the cell
            String oldContent = content.toString();
            String arr[] = sourceCode.split(" ", 2);
            String newContent = (arr.length > 1) ? arr[1] : "";
            content = new Text(newContent);
        }
        
        if (content.isError()) {
            System.out.println(content.toString());
            return false;
        }
        // set the new cell content
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
