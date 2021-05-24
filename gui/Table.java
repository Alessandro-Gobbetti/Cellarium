package gui;

import javax.swing.JTable;

/**
 * Creates the Table for the Spreadsheet.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class Table {
    
    /**
     * Creates the a ToolBar for the Spreadsheet.
     * @param spreadsheetView  to handle the table.
     * @return JTable          the Table of the spreadsheet.
     */
    public JTable createTable(final SpreadsheetViewTableModel spreadsheetView) {
        //Table 
        final JTable table = new JTable(spreadsheetView);
        //Enables selection of Cells
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        
        table.setDefaultRenderer(String.class, new SpreadsheetViewTableRenderer());
        
        return table;
    }
}
