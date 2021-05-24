package graphicalUserInterface;

import spreadsheet.*;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


/**
 * Write a description of class SpreadsheetTableModel here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetViewTableModel extends AbstractTableModel {
    
    private Spreadsheet spreadsheet;
    private int originRow;
    private int originCol;
    private int rowCount;
    private int columnCount;

    /**
     * Constructor for objects of class SpreadsheetTableModel.
     * @param spreadsheet  a spreadsheet to compute on.
     */
    public SpreadsheetViewTableModel(final Spreadsheet spreadsheet) {
        super();
        originRow = 1;
        originCol = 1;
        rowCount = 60;
        columnCount = 30;
        this.spreadsheet = spreadsheet;
    }

    /**
     * Returns the value of rowCount.
     * @return the rowCount.
     */
    public int getRowCount() {
        return rowCount;
    }
    
    /**
     * Returns the value of columnCount.
     * @return the columnCount.
     */
    public int getColumnCount() {
        return columnCount;
    }
    
    /**
     * Returns the value of originRow.
     * @return the originRow.
     */
    public int getOriginRow() {
        return originRow;
    }
    
    /**
     * Returns the value of originCol.
     * @return the originCol.
     */
    public int getOriginCol() {
        return originCol;
    }
    
    private int viewToSpreadsheetRow(final int row) {
        return row - 2 + originRow;
    }
    
    private int viewToSpreadsheetCol(final int col) {
        return col - 2 + originCol;
    }
    
    private int spreadsheetToViewRow(final int row) {
        return row + 2 - originRow;
    }
    
    private int spreadsheetToViewCol(final int col) {
        return col + 2 - originCol;
    }
    
    /**
     * Defines whether the Cell is a normal cell or it has to show 
     * the index.
     * @param row  the row of the Cell.
     * @param col  the column of the Cell.
     * @return the value of the Cell.
     */
    public Object getValueAt(final int row, final int col) {
        String result = "";
        final int r = viewToSpreadsheetRow(row);
        final int c = viewToSpreadsheetCol(col);
        if (col == 0) {
            if (row > 0) {
                result = "" + (r + 1);
            }
        } else if  (row == 0) {
            if (col > 0) {
                result = CellReference.toAlpha26(c);
            }
        } else {
            if (spreadsheet.exists(r, c)) {
                final Cell cell = spreadsheet.getOrCreate(r, c);
                result = cell.eval().asString();
                //result = "(" + r + "," + c + ")";
            }
        }
        return result;
    }
    
    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return rowIndex > 0 && columnIndex > 0;
    }
    
    @Override
    public Class<?> getColumnClass(final int col) {
        return String.class;
    }
    
    @Override
    public void setValueAt(final Object aValue, final int row, final int col) {
        final String sourceCode = (String)aValue;
        final int r = viewToSpreadsheetRow(row);
        final int c = viewToSpreadsheetCol(col);
        
        final CellariumParser parser = new CellariumParser();
        parser.initLexer(sourceCode);
        // parse the new content of the cell
        final Node content = parser.parseCell();
        
        final Cell cell = spreadsheet.getOrCreate(r, c);
        final ArrayList<Cell> markedOutOfDate = new ArrayList<Cell>();
        cell.setFormulaAndGetOutdatedCells(content, markedOutOfDate);
        //fireTableDataChanged(); //FIXME set formula should return the changed cells...
        for (final Cell outdatedCell : markedOutOfDate) {
            final int outdatedRow = spreadsheetToViewRow(outdatedCell.getRow());
            final int outdatedCol = spreadsheetToViewCol(outdatedCell.getCol());
            fireTableCellUpdated(outdatedRow, outdatedCol);
        }
    }
    
    /**
     * Cheanges the origin Cell and updates the table.
     * @param row  the row of the Cell.
     * @param col  the column of the Cell.
     */
    public void setOrigin(final int row, final int col) {
        originRow = row;
        originCol = col;
        fireTableDataChanged();
    }
}
