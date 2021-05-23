import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Write a description of class SpreadsheetTableModel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetViewTableModel extends AbstractTableModel {
    
    private Spreadsheet spreadsheet;
    private int originRow;
    private int originCol;
    private int rowCount;
    private int columnCount;

    /**
     * Constructor for objects of class SpreadsheetTableModel
     */
    public SpreadsheetViewTableModel(Spreadsheet spreadsheet) {
        originRow = 1;
        originCol = 1;
        rowCount = 60;
        columnCount = 30;
        this.spreadsheet = spreadsheet;
    }

    public int getRowCount() {
        return rowCount;
    }
    
    public int getColumnCount() {
        return columnCount;
    }
    
    public int getOriginRow() {
        return originRow;
    }
    
    public int getOriginCol() {
        return originCol;
    }
    
    private int viewToSpreadsheetRow(int row) {
        return row - 2 + originRow;
    }
    
    private int viewToSpreadsheetCol(int col) {
        return col - 2 + originCol;
    }
    
    private int spreadsheetToViewRow(int row) {
        return row + 2 - originRow;
    }
    
    private int spreadsheetToViewCol(int col) {
        return col + 2 - originCol;
    }
    
        
    public Object getValueAt(int row, int col) {
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
                Cell cell = spreadsheet.getOrCreate(r, c);
                result = cell.eval().asString();
                //result = "(" + r + "," + c + ")";
            }
        }
        return result;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex > 0 && columnIndex > 0;
    }
    
    @Override
    public Class<?> getColumnClass(int col) {
        return String.class;
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int col) {
        String sourceCode = (String)aValue;
        final int r = viewToSpreadsheetRow(row);
        final int c = viewToSpreadsheetCol(col);
        
        final CellariumParser parser = new CellariumParser();
        parser.initLexer(sourceCode);
        // parse the new content of the cell
        Node content = parser.parseCell();
        
        final Cell cell = spreadsheet.getOrCreate(r, c);
        ArrayList<Cell> markedOutOfDate = new ArrayList<Cell>();
        cell.setFormulaAndGetOutdatedCells(content, markedOutOfDate);
        //fireTableDataChanged(); //FIXME set formula should return the changed cells...
        for (Cell outdatedCell : markedOutOfDate) {
            int outdatedRow = spreadsheetToViewRow(outdatedCell.getRow());
            int outdatedCol = spreadsheetToViewCol(outdatedCell.getCol());
            fireTableCellUpdated(outdatedRow, outdatedCol);
        }
    }
    
    public void setOrigin(int row, int col) {
        originRow = row;
        originCol = col;
        fireTableDataChanged();
    }
}
