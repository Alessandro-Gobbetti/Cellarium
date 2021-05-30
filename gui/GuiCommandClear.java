package gui;

import commands.UndoableStateChangingCommand;
import lexer.TokenType;
import spreadsheet.Cell;
import spreadsheet.CellReference;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;

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
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            for (final int index: spreadsheet.getCellMap().keySet()) {
                final int row = spreadsheet.rowFromIndex(index);
                final int col = spreadsheet.colFromIndex(index);
                final Node formula = spreadsheet.get(row, col).getFormulaNode();
                stateSaved.put(index, formula);
            }
            spreadsheetView.clear();
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
            final int col = cellReference.getCol(0);
            final int index = spreadsheet.indexFromRowCol(row, col);
            stateSaved.put(index, spreadsheetView.getSpreadsheetOldAndRemoveAt(row, col));
            setLastOperationOk();
        }
    }
    
    @Override
    public void undo() {
        final Spreadsheet spreadsheet = spreadsheetView.getSpreadsheet();
        for (int index : stateSaved.keySet()) {
            final int row = spreadsheet.rowFromIndex(index);
            final int col = spreadsheet.colFromIndex(index);
            final Cell cell = spreadsheet.getOrCreate(row, col);
            cell.setFormula(stateSaved.get(index));
        }
        spreadsheetView.fireTableDataChanged();
        setLastOperationOk();
    }
    
    @Override
    public void redo() {
        doit();
    }
}
