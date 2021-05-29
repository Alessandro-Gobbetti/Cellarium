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
    private HashMap<Cell, Node> stateSaved;
    

    /**
     * Creator for TuiCommandSet.
     */
    public TuiCommandClear(final String sourceCode, final Spreadsheet spreadsheet) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
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
                stateSaved.put(cell, cell.getFormulaNode());
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
            final Cell cell = spreadsheet.getOrCreate(cellReference.getRow(0), 
                                                      cellReference.getCol(0));
            stateSaved.put(cell, cell.getFormulaNode());
            spreadsheet.remove(cellReference.getRow(0), cellReference.getCol(0));
            setLastOperationOk();
        }
    }
    
    
    @Override
    public void undo() {
        for (Cell cell : stateSaved.keySet()) {
            cell.setFormula(stateSaved.get(cell));
        }
    }
    
    @Override
    public void redo() {
        doit();
    }
}
