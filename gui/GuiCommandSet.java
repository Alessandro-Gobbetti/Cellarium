package gui;

import commands.UndoableStateChangingCommand;
import spreadsheet.CellReference;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Text;

/**
 * To set a cell content.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandSet extends UndoableStateChangingCommand {
    
    private String sourceCode;
    private SpreadsheetViewTableModel spreadsheetView;
    private Node stateSavedFormula;
    private CellReference stateChangedCellReference;
    

    /**
     * Creator for GuiCommandSet.
     * 
     * @param sourceCode the source code
     * @param spreadsheetView the view table model
     */
    public GuiCommandSet(final String sourceCode,
                         final SpreadsheetViewTableModel spreadsheetView) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheetView = spreadsheetView;
        this.stateSavedFormula = null;
        this.stateChangedCellReference = null;
    }
    
    @Override
    public String getName() {
        return "Set";
    }

    @Override
    public void doit() {
        final CellariumParser parser = new CellariumParser(spreadsheetView.getSpreadsheet());
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
        } else {
            // set the new cell content and save the old content.
            stateSavedFormula =
                spreadsheetView.getSpreadsheetOldAndSetNewAt(content,
                                                             stateChangedCellReference.getRow(0), 
                                                             stateChangedCellReference.getCol(0));
            setLastOperationOk();
        }
    }
    
    @Override
    public void undo() {
        if (stateChangedCellReference == null) {
            setLastOperationStatus(false, true, "Missing reference");
        } else {
            stateSavedFormula =
                spreadsheetView.getSpreadsheetOldAndSetNewAt(stateSavedFormula,
                                                             stateChangedCellReference.getRow(0), 
                                                             stateChangedCellReference.getCol(0));
            
            setLastOperationOk();
        }
    }
    
    @Override
    public void redo() {
        undo();
    }
}
