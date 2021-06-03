package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Handels the colors of different sections of the Spreadsheet.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetViewTableRenderer extends DefaultTableCellRenderer {
    
    private final Font font;
    
    /**
     * Constructor of SpreadsheetViewTableRenderer.
     * Defines the style and font.
     */
    public SpreadsheetViewTableRenderer() {
        super();
        super.setOpaque(true);
        font = new Font("SansSerif", Font.PLAIN, 14);
    }
     
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, 
                                                   final boolean isSelected, final boolean hasFocus,
                                                   final int row, final int column) {
        if (row == 0 || column == 0) {
            renderBoundary(table, value, row, column);
        } else {
            renderSpreadsheet(table, value, isSelected, row, column);
        }
        return this;
    }
    
    /**
     * to render the spreadsheet boundary cell.
     * 
     * @param table the table
     * @param value the value to insert
     * @param row the row
     * @param column the column
     */
    private void renderBoundary(final JTable table, final Object value, 
                                final int row, final int column) {
        assert row == 0 || column == 0;
        // set default font  
        super.setFont(font);
        setHorizontalAlignment(row == 0 ? JLabel.CENTER : JLabel.RIGHT); 
        if (row == 0) {
            super.setBackground(column == table.getSelectedColumn()
                                ? Color.ORANGE
                                : Color.DARK_GRAY);
            super.setForeground(column == table.getSelectedColumn()
                                ? Color.BLACK
                                : Color.WHITE);
            super.setValue(value);
        } else if (column == 0) {
            super.setBackground(row == table.getSelectedRow() 
                                ? Color.ORANGE
                                : Color.DARK_GRAY);
            super.setForeground(row == table.getSelectedRow()
                                ? Color.BLACK
                                : Color.WHITE);
            super.setValue(value + "      ");
        }
    }
    
    /**
     * to render the spreadsheet cell.
     * 
     * @param table the table
     * @param value the value to insert
     * @param isSelected true if the cell is selected
     * @param row the row
     * @param column the column
     */
    private void renderSpreadsheet(final JTable table, final Object value, 
                                   final boolean isSelected,
                                   final int row, final int column) {
        assert row != 0 || column != 0;
        final SpreadsheetViewTableModel tableModel = 
            (SpreadsheetViewTableModel)(table.getModel());
        final boolean isError = tableModel.isErrorAt(row, column);
        final boolean isNumber = tableModel.isNumberAt(row, column);
        super.setFont(getFont().deriveFont(isNumber ? Font.PLAIN : Font.ITALIC));
        super.setHorizontalAlignment(isNumber ? JLabel.RIGHT : JLabel.LEFT);
        super.setForeground(isError ? Color.RED : Color.BLACK);
        super.setBackground(isSelected ? Color.ORANGE : Color.WHITE);
        super.setValue(value);
    }
}
