package gui;

import spreadsheet.CellReference;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

/**
 * Write a description of class SpreadsheetTerminalHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetMouseHandler extends MouseAdapter {
    
    private SpreadsheetViewTableModel spreadsheetView;
    private JTextField terminal;
    private Table table;
    
    public SpreadsheetMouseHandler(final SpreadsheetViewTableModel spreadsheetView,
                                   final JTextField terminal,
                                   final Table table) {
       this.spreadsheetView = spreadsheetView;
       this.terminal = terminal;
       this.table = table;
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {
        final int viewRow = table.rowAtPoint(e.getPoint());
        final int viewCol = table.columnAtPoint(e.getPoint());
        // to force repainting the row 0 to update selected column.
        spreadsheetView.fireTableRowsUpdated(0,0);
                
        if (viewRow > 0 && viewCol > 0) {
            //we selected a spreadsheet cell: get the formula and update the terminal
            final int row = spreadsheetView.viewToSpreadsheetRow(viewRow);
            final int col = spreadsheetView.viewToSpreadsheetCol(viewCol);
            final String formula = spreadsheetView.getFormulaAt(viewRow, viewCol);
            String command;
            if(spreadsheetView.isErrorAt(viewRow, viewCol)) {
                command = formula;
                terminal.setForeground(Color.RED);
            } else {
                command = "SET " + CellReference.toA1(false, row, false, col) + " " + formula;
                terminal.setForeground(Color.BLACK);
            }
            terminal.setText(command);
        }
    }

}
