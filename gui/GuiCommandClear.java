package gui;

import commands.UndoableStateChangingCommand;
import spreadsheet.lexer.TokenType;
import spreadsheet.Cell;
import spreadsheet.CellReference;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;

import java.util.HashMap;

/**
 * Write a description of class guiCommandSet here.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandClear extends UndoableStateChangingCommand {
    
    private String sourceCode;
    private SpreadsheetViewTableModel spreadsheetView;
    private HashMap<Integer, Node> stateSaved;
    

    /**
     * Creator for GuiCommandClear.
     * 
     * @param sourceCode the command to parse
     * @param spreadsheetView the view table model
     */
    public GuiCommandClear(final String sourceCode,
                           final SpreadsheetViewTableModel spreadsheetView) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheetView = spreadsheetView;
        this.stateSaved = new HashMap<Integer, Node>();
    }
    
    @Override
    public String getName() {
        return "Clear";
    }

    @Override
    public void doit() {
        final CellariumParser parser = new CellariumParser(spreadsheetView.getSpreadsheet());
        parser.initLexer(sourceCode);
        stateSaved.clear();
        final Spreadsheet spreadsheet = spreadsheetView.getSpreadsheet();
        if (!parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            final Cell cell = parser.getCellFromParsedReference();
            if (cell == null) {
                setLastOperationStatus(false, true, "Syntax Error");
                return;
            }
            final int row = cell.getRow();
            final int col = cell.getCol();
            final int index = spreadsheet.indexFromRowCol(row, col);
            stateSaved.put(index, spreadsheetView.getSpreadsheetOldAndRemoveAt(row, col));
            setLastOperationOk();
        } else {
            spreadsheet.saveStateIn(stateSaved);
            spreadsheetView.clear();
            setLastOperationOk();
        }
    }
    
    @Override
    public void undo() {
        final Spreadsheet spreadsheet = spreadsheetView.getSpreadsheet();
        spreadsheet.restoreStateFrom(stateSaved);
        spreadsheetView.fireTableDataChanged();
        stateSaved.clear();
        setLastOperationOk();
    }
    
    @Override
    public void redo() {
        doit();
    }
}
