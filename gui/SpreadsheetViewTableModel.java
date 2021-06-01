package gui;

import spreadsheet.Cell;
import spreadsheet.CellReference;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


/**
 * Handles how the spreadsheet has to be showed.
 * Creates the index lines for columns and rows. 
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
    private GuiCommandInterpreter interpreter;

    /**
     * Constructor for objects of class SpreadsheetTableModel.
     * 
     * @param spreadsheet  a spreadsheet to compute on.
     * @param interpreter the interpreter.
     */
    public SpreadsheetViewTableModel(final Spreadsheet spreadsheet,
                                     final GuiCommandInterpreter interpreter) {
        super();
        originRow = 1;
        originCol = 1;
        rowCount = 60;
        columnCount = 30;
        this.spreadsheet = spreadsheet;
        this.interpreter = interpreter;
    }
    
    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return rowIndex > 0 && columnIndex > 0;
    }
    
    //Setter methods
    /**
     * Cheanges the origin Cell and updates the table.
     * 
     * @param row  the row of the Cell.
     * @param col  the column of the Cell.
     */
    public void setOrigin(final int row, final int col) {
        originRow = row;
        originCol = col;
        fireTableDataChanged();
    }
    
    @Override
    public void setValueAt(final Object aValue, final int row, final int col) {
        final String sourceCode = (String)aValue;
        final int r = viewToSpreadsheetRow(row);
        final int c = viewToSpreadsheetCol(col);
        final String command = "SET " + CellReference.toA1(false, r, false, c) + " " + sourceCode;
        interpreter.parseAndExecute(command, this);
    }
    
    
    /**
     * To set a new formula at a position in the spreadsheet
     * and get the old value.
     * 
     * @param formula the formula to insert
     * @param row the row
     * @param col the column
     * @return the old formula.
     */
    public Node getSpreadsheetOldAndSetNewAt(final Node formula, final int row, final int col) {
        final Cell cell =  spreadsheet.getOrCreate(row, col);
        final Node result = cell.getFormulaNode();
        
        final ArrayList<Cell> markedOutOfDate = new ArrayList<Cell>();
        cell.setFormulaAndGetOutdatedCells(formula, markedOutOfDate);
        //fireTableDataChanged(); //to update all the table cells.
        for (final Cell outdatedCell : markedOutOfDate) {
            final int outdatedRow = spreadsheetToViewRow(outdatedCell.getRow());
            final int outdatedCol = spreadsheetToViewCol(outdatedCell.getCol());
            fireTableCellUpdated(outdatedRow, outdatedCol);
        }
        return result;
    }
    
    /**
     * To clear a cell and return the old formula.
     * 
     * @param row the row
     * @param col the column
     * @return the old formula
     */
    public Node getSpreadsheetOldAndRemoveAt(final int row, final int col) {
        final Node result = getSpreadsheetOldAndSetNewAt(null, row, col);
        spreadsheet.remove(row, col);
        return result;
    }
    
    /**
     * To clear the spreadsheet.
     */
    public void clear() {
        spreadsheet.clear();
        fireTableDataChanged();
    }
    
    // Getter methods.
    @Override
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
                final Cell cell = spreadsheet.get(r, c);
                result = cell.eval().asString();
            }
        }
        return result;
    }
    
    /**
     * To get the formula in a specific cell.
     * 
     * @param row the row to look at
     * @param col the column to look at
     * @return the formula in the cell.
     */
    public String getFormulaAt(final int row, final int col) {
        String result = "";
        final int r = viewToSpreadsheetRow(row);
        final int c = viewToSpreadsheetCol(col);
        if (col > 0 && row > 0) {
            result = spreadsheet.getFormula(r, c);
        }
        return result;
    }
    
    /**
     * True if the cell contains a number, false otherwise.
     * 
     * @param row the row to look at
     * @param col the column to look at
     * @return true if the cell content is a number. 
     */
    public boolean isNumberAt(final int row, final int col) {
        boolean result = false;
        final int r = viewToSpreadsheetRow(row);
        final int c = viewToSpreadsheetCol(col);
        if (col > 0 && row > 0) {
            if (spreadsheet.exists(r, c)) {
                final Cell cell = spreadsheet.get(r, c);
                result  = cell.eval().isConvertibleToNumber();
            }
        }
        return result;
    }
    
    /**
     * True if the cell contains an error, false otherwise.
     * 
     * @param row the row to look at
     * @param col the column to look at
     * @return true if the cell contains an error, false otherwise.
     */
    public boolean isErrorAt(final int row, final int col) {
        boolean result = false;
        final int r = viewToSpreadsheetRow(row);
        final int c = viewToSpreadsheetCol(col);
        if (row > 0 && col > 0) {
            if (spreadsheet.exists(r, c)) {
                final Cell cell = spreadsheet.get(r, c);
                result = cell.eval().isError();
            }
        }
        return result;
    }      
            
    @Override
    public Class<?> getColumnClass(final int col) {
        return String.class;
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
    
    /**
     * Return the row dimension of the spreadsheet.
     * @return the row dimension of the spreadsheet.
     */
    public int getSpreadsheetRowDimension() {
        final int maxRow = spreadsheet.getMaxUsedCellRow();
        return maxRow > rowCount ? maxRow : rowCount;
    }
    
    /**
     * Return the column dimension of the spreadsheet.
     * @return the column dimension of the spreadsheet.
     */
    public int getSpreadsheetColDimension() {
        final int maxCol = spreadsheet.getMaxUsedCellCol();
        return maxCol > columnCount ? maxCol : columnCount;
    }
    
    /**
     * Return the spreadsheet.
     * @return the spreadsheet.
     */
    public Spreadsheet getSpreadsheet() {
        return spreadsheet;
    }
    
    
    // To convert from view to spreadsheet model and vice-versa.
    /**
     * Convert view row index to spreadsheet row index.
     * 
     * @param row the row to covert.
     * @return the converted row index.
     */
    public int viewToSpreadsheetRow(final int row) {
        return row - 2 + originRow;
    }
    
    /**
     * Convert view column index to spreadsheet column index.
     *  
     * @param col the column to covert.
     * @return the converted column index.
     */
    public int viewToSpreadsheetCol(final int col) {
        return col - 2 + originCol;
    }
    
    /**
     * Convert a spreadsheet row index to view row index.
     *  
     * @param row the row to covert.
     * @return the converted row index.
     */
    public int spreadsheetToViewRow(final int row) {
        return row + 2 - originRow;
    }
    
    /**
     * Convert a spreadsheet column index to view column index.
     *  
     * @param col the column to covert.
     * @return the converted column index.
     */
    public int spreadsheetToViewCol(final int col) {
        return col + 2 - originCol;
    }
}
