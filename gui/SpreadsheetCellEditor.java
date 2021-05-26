package gui;

import spreadsheet.CellReference;

import java.awt.Color;
import javax.swing.DefaultCellEditor;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.AbstractCellEditor;
import java.util.EventObject;
import java.awt.event.MouseEvent;

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
    
    public SpreadsheetCellEditor(final SpreadsheetViewTableModel spreadsheetView) {
        textfield = new JTextField();
        this.spreadsheetView = spreadsheetView;
    }
    
    public Object getCellEditorValue () {
        return textfield.getText();
    }
    
    // to start editing only when clicked twice.
    @Override
    public boolean isCellEditable(EventObject aAnEvent) {
        boolean cellEditable = super.isCellEditable(aAnEvent);

        if (cellEditable && aAnEvent instanceof MouseEvent) {
        cellEditable = ((MouseEvent) aAnEvent).getClickCount() == 2;
        }

        return cellEditable;
    }
    
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int viewRow, int viewCol) {
        assert viewRow > 0;
        assert viewCol > 0;
        // to print the formula inside the cell when starting editing
        final String formula = spreadsheetView.getFormulaAt(viewRow, viewCol);
        textfield.setText(formula);
        textfield.setBackground(Color.LIGHT_GRAY);
        textfield.selectAll(); // automatically select the whole string in the cell
        return textfield;
    }
}
