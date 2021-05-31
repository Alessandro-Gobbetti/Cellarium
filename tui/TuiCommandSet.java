package tui;

import commands.UndoableStateChangingCommand;
import spreadsheet.Cell;
import spreadsheet.CellReference;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;

/**
 * To Set a new cell value.
 * z`
 * <p>
 * SET A1 23
 * SET CELLREFERENCE CELL
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandSet extends UndoableStateChangingCommand {
    
    private String sourceCode;
    private Spreadsheet spreadsheet;
    private Node stateSavedFormula;
    private CellReference stateChangedCellReference;
    

    /**
     * Creator for TuiCommandSet.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
     */
    public TuiCommandSet(final String sourceCode, final Spreadsheet spreadsheet) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
        this.stateSavedFormula = null;
        this.stateChangedCellReference = null;
    }
    
    @Override
    public String getName() {
        return "Set";
    }

    @Override
    public void doit() {
        final CellariumParser parser = new CellariumParser(spreadsheet);
        parser.initLexer(sourceCode);
        // parse the cell referernce
        final Node node = parser.parseCellReference();
        if (!(node instanceof CellReference)) {
            setLastOperationStatus(false, true, "Syntax Error");
            return;
        }
        stateChangedCellReference = (CellReference)node;
        // parse the new content of the cell
        Node content = parser.parseCell();
        
        if (content instanceof Text) {
            // remove cell reference from text to print in the cell
            final String[] arr = sourceCode.split(" ", 2);
            final String newContent = arr.length > 1 ? arr[1] : "";
            content = new Text(newContent);
        }
        
        if (content.isError()) {
            setLastOperationStatus(false, true, content.toString());
            return;
        }
        // set the new cell content
        final Cell cell =  spreadsheet.getOrCreate(stateChangedCellReference.getRow(0), 
                                                   stateChangedCellReference.getCol(0));
        stateSavedFormula = cell.getFormulaNode();
        cell.setFormula(content);
        setLastOperationOk();
    }
    
    @Override
    public void undo() {
        if (stateChangedCellReference == null) {
            setLastOperationStatus(false, true, "Missing reference");
            return;
        } else {
            final Cell cell =  spreadsheet.getOrCreate(stateChangedCellReference.getRow(0), 
                                                       stateChangedCellReference.getCol(0));
            final Node content = stateSavedFormula;
            stateSavedFormula = cell.getFormulaNode();
            cell.setFormula(content);
            setLastOperationOk();
        }
    }
    
    @Override
    public void redo() {
        // redo undo the undo :-)
        undo();
    }
    
}
