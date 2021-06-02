package tui;

import commands.UndoableStateChangingCommand;
import spreadsheet.lexer.TokenType;
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
     * Creator for TuiCommandClear.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
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
        stateSaved.clear();
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            spreadsheet.saveStateIn(stateSaved);
            spreadsheet.clear();
            setLastOperationOk();
        } else {
            final Cell cell = parser.getCellFromParsedReference();
            if (cell == null) {
                setLastOperationStatus(false, true, "Syntax Error");
                return;
            }
            final int row = cell.getRow();
            final int col = cell.getCol();
            final int index = spreadsheet.indexFromRowCol(row, col);
            stateSaved.put(index, cell.getFormulaNode());
            spreadsheet.remove(row, col);
            setLastOperationOk();
        }
    }
    
    
    @Override
    public void undo() {
        spreadsheet.restoreStateFrom(stateSaved);
        stateSaved.clear();
        setLastOperationOk();
    }
    
    @Override
    public void redo() {
        doit();
    }
}
