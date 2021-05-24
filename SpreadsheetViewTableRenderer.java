import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Font;

/**
 * Write a description of class SpreadsheetViewTableRenderer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetViewTableRenderer extends DefaultTableCellRenderer {
    
    private final Font font;
    
    public SpreadsheetViewTableRenderer() {
        super.setOpaque(true);
        font = new Font("SansSerif", Font.PLAIN, 14);
    }
     
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        super.setFont(font);
        if (row == 0) {
            if (table.isColumnSelected(column)) {
                super.setBackground(Color.ORANGE);
            } else {
                super.setBackground(Color.GRAY);
            }
            super.setForeground(Color.WHITE);
        } else if (column == 0) {
            if (table.isRowSelected(row)) {
                super.setBackground(Color.ORANGE);
            } else {
                super.setBackground(Color.GRAY);
            }
            super.setForeground(Color.WHITE);
        // } else if (isSelected && hasFocus) {
            // super.setBackground(Color.ORANGE);
        // } else if (isSelected) {
            // super.setBackground(Color.YELLOW);
        } else if (hasFocus) {
            super.setBackground(Color.RED);
            super.setForeground(Color.BLACK);
        } else {
            super.setBackground(Color.WHITE);
            super.setForeground(Color.BLACK);
        }
        super.setValue(value);
        return this;
    }
}
