
/**
 * Write a description of class SpreadsheetSet here.
 * 
 * <p>
 * SET A1 23
 * SET CELLREFERENCE CELL
 * <p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetCommandSet implements SpreadsheetCommand {

    /**
     * Create a new Cos node.
     * @param sourceCode
     * @pram spreadsheet  the used spreadsheet.
     * @return boolean
     */
    @Override
    public boolean parseAndExecute(final String sourceCode, final Spreadsheet spreadsheet) {
        final CellariumParser parser = new CellariumParser();
        parser.initLexer(sourceCode);
        // parse the cell referernce
        final Node node = parser.parseCellReference();
        if (!(node instanceof CellReference)) {
            return false;
        }
        final CellReference cellReference = (CellReference) node;
        // parse the new content of the cell
        Node content = parser.parseCell();
        
        if (content instanceof Text) {
            // remove cell reference from text to print in the cell
            final String oldContent = content.toString();
            final String arr[] = sourceCode.split(" ", 2);
            final String newContent = arr.length > 1 ? arr[1] : "";
            content = new Text(newContent);
        }
        
        if (content.isError()) {
            System.out.println(content.toString());
            return false;
        }
        // set the new cell content
        final Cell cell = spreadsheet.getOrCreate(cellReference.getRow(0), cellReference.getCol(0));
        cell.setFormula(content);
        return true;
    }
    
    /**
     * Create a new Cos node.
     * @return 
     */
    public String helpShort() {
        return "Set the value of a cell";
    }
    
    /**
     * Create a new Cos node.
     * @param commandName  the commandName.
     * @return 
     */
    public String helpLong(final String commandName) {
        return commandName + " CellReference Formula: to set the content of a cell.\n"
               + "Ex: SET A1 hello, to set the string \"hello\" as content of cell A1.\n"
               + "Ex: SET A1 = A2 + 3, to set A1 as the sum of the content of A1 plus 3.";
    }
}
