package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 * Write a description of class s here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetCellEditor extends AbstractCellEditor implements TableCellEditor {
    
    private JTextField textfield;
    private SpreadsheetViewTableModel spreadsheetView;
    
    /**
     * Constructor for SpreadsheetCellEditor.
     * 
     * @param spreadsheetView the view table model
     */
    public SpreadsheetCellEditor(final SpreadsheetViewTableModel spreadsheetView) {
        super();
        textfield = new JTextField();
        this.spreadsheetView = spreadsheetView;
    }
    
    @Override
    public Object getCellEditorValue() {
        return textfield.getText();
    }
    
    // to start editing only when clicked twice.
    @Override
    public boolean isCellEditable(final EventObject aAnEvent) {
        boolean cellEditable = super.isCellEditable(aAnEvent);

        if (cellEditable && aAnEvent instanceof MouseEvent) {
            cellEditable = ((MouseEvent) aAnEvent).getClickCount() == 2;
        }
        
        return cellEditable;
    }
    
    /**
     * To get the formula instead of the value when starting editing a cell.
     */
    @Override
    public Component getTableCellEditorComponent(final JTable table, final Object value,
                                                 final boolean isSelected,
                                                 final int viewRow, final int viewCol) {
        assert viewRow > 0;
        assert viewCol > 0;
        // to print the formula inside the cell when starting editing
        final String formula = spreadsheetView.getFormulaAt(viewRow, viewCol);
        textfield.setText(formula);
        textfield.setBackground(new Color(230, 230, 230));
        textfield.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        textfield.selectAll(); // automatically select the whole string in the cell
        return textfield;
    }
}
