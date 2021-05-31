package gui;

import spreadsheet.Cell;
import spreadsheet.Spreadsheet;

import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JTable;

/**
 * Creates the Table for the Spreadsheet.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class Table extends JTable {
    
    /**
     * Creates the a Table.
     * @param spreadsheetView  the model of the visible table.
     */
    public Table(final SpreadsheetViewTableModel spreadsheetView) {
        //Table
        super(spreadsheetView);
        //Enables selection of Cells
        setRowSelectionAllowed(true);
        setColumnSelectionAllowed(true);
        setGridColor(Color.BLACK);
        
        setDefaultEditor(String.class, new SpreadsheetCellEditor(spreadsheetView));
        setDefaultRenderer(String.class, new SpreadsheetViewTableRenderer());
    }
    
    /**
     * To get the selectedcell.
     * 
     * @param spreadsheet the spreadsheet.
     * 
     * @return the selected cell.
     */
    public Cell getSelectedCell(final Spreadsheet spreadsheet) {
        final int selectedRow = getSelectedRow();
        final int selectedCol = getSelectedColumn();
        if (selectedRow < 0 || selectedCol < 0) {
            return spreadsheet.getOrCreate(0, 0);
        }
        return spreadsheet.getOrCreate(selectedRow, selectedCol);
    }
    
    /**
     * To get the index of first column displayed.
     * 
     * @return the index of first column displayed.
     */
    public int getFirstVisibleRow() {
        final Rectangle vr = getVisibleRect();
        return rowAtPoint(vr.getLocation());
    }
    
    /**
     * To get the index of first column displayed.
     * 
     * @return the index of first column displayed.
     */
    public int getFirstVisibleCol() {
        final Rectangle vr = getVisibleRect();
        return columnAtPoint(vr.getLocation());
    }
    
    /**
     * To get the index of last row displayed.
     * 
     * @return the index of last row displayed.
     */
    public int getLastVisibleRow() {
        final Rectangle vr = getVisibleRect();
        vr.translate(0, vr.height);
        return rowAtPoint(vr.getLocation());
    }
    
    /**
     * To get the index of last column displayed.
     * 
     * @return the index of last column displayed.
     */
    public int getLastVisibleCol() {
        final Rectangle vr = getVisibleRect();
        vr.translate(vr.width, 0);
        return columnAtPoint(vr.getLocation());
    }
    
    /**
     * To get the number of rows displayed.
     * 
     * @return the number of rows displayed.
     */
    public int getNumberOfVisibleRows() {
        return getLastVisibleRow() - getFirstVisibleRow() + 1;
    }
    
    /**
     * To get the number of columns displayed.
     * 
     * @return the number of columns displayed.
     */
    public int getNumberOfVisibleCols() {
        return getLastVisibleCol() - getFirstVisibleCol() + 1;
    }
}
