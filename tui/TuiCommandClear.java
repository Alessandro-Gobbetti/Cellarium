package tui;

import commands.UndoableStateChangingCommand;
import lexer.TokenType;
import spreadsheet.Cell;
import spreadsheet.CellReference;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;

import java.util.HashMap;

/**
 * To clear all the spreadsheet or just a cell.
 * 
 * </p>
 * CLEAR         to clear all the spreadsheet
 * CLEAR A1      to clear a cell only
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandClear extends UndoableStateChangingCommand {

    private String sourceCode;
    private Spreadsheet spreadsheet;
    private HashMap<Integer, Node> stateSaved;
    

    /**
     * Creator for TuiCommandSet.
     */
    public TuiCommandClear(final String sourceCode, final Spreadsheet spreadsheet) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
        this.stateSaved = new HashMap<Integer, Node>();
    }
    
    @Override
    public String getName() {
        return "Clear";
    }

    @Override
    public void doit() {
        final CellariumParser parser = new CellariumParser(spreadsheet);
        parser.initLexer(sourceCode);
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            for (final int index: spreadsheet.getCellMap().keySet()) {
                final int row = spreadsheet.rowFromIndex(index);
                final int col = spreadsheet.colFromIndex(index);
                final Cell cell = spreadsheet.get(row, col);
                stateSaved.put(index, cell.getFormulaNode());
            }
            spreadsheet.clear();
            setLastOperationOk();
        } else {
            final Node node = parser.parseCellReference();
            if (!(node instanceof CellReference)) {
                setLastOperationStatus(false, true, "Syntax Error");
                return;
            }
            final CellReference cellReference = (CellReference)node;
            if (!parser.currentTokenMatches(TokenType.END_OF_FILE)) {
                setLastOperationStatus(false, true, "Reached end of file wile parsing");
                return;
            }
            final int row = cellReference.getRow(0);
            final int col = cellReference.getRow(0);
            final int index = spreadsheet.indexFromRowCol(row, col);
            final Cell cell = spreadsheet.getOrCreate(row, col);
            stateSaved.put(index, cell.getFormulaNode());
            spreadsheet.remove(cellReference.getRow(0), cellReference.getCol(0));
            setLastOperationOk();
        }
    }
    
    
    @Override
    public void undo() {
        for (int index : stateSaved.keySet()) {
            final int row = spreadsheet.rowFromIndex(index);
            final int col = spreadsheet.colFromIndex(index);
            final Cell cell = spreadsheet.getOrCreate(row, col);
            cell.setFormula(stateSaved.get(index));
        }
    }
    
    @Override
    public void redo() {
        doit();
    }
}
