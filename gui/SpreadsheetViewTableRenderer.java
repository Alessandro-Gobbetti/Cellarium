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
 * @author Alessandro Gobbetti && Laurenz Ebi
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
        // set content allignment and font
        setHorizontalAlignment(JLabel.CENTER);     
        super.setFont(font);
        
        if (row == 0) {
            super.setBackground(column == table.getSelectedColumn()
                                ? Color.ORANGE
                                : Color.DARK_GRAY);
            super.setForeground(column == table.getSelectedColumn()
                                ? Color.BLACK
                                : Color.WHITE);
            super.setValue(value);
        } else if (column == 0) {
            super.setHorizontalAlignment(JLabel.RIGHT);
            super.setBackground(row == table.getSelectedRow() 
                                ? Color.ORANGE
                                : Color.DARK_GRAY);
            super.setForeground(row == table.getSelectedRow()
                                ? Color.BLACK
                                : Color.WHITE);
            super.setValue(value + "      ");
        } else {
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
        return this;
    }
}
