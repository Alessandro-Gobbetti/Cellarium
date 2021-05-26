package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the actions of in the Program window.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class Actions implements ActionListener {
    
    private SpreadsheetViewTableModel spreadsheetView;
    
    /**
     * Constrctor of Actions.
     * 
     * @param spreadsheetView  the SpreadsheetViewTableModel reference object.
     */
    public Actions(final SpreadsheetViewTableModel spreadsheetView) {
        this.spreadsheetView = spreadsheetView;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if ("View up".equals(e.getActionCommand())) {
            final int row = spreadsheetView.getOriginRow();
            if (row > 1) {
                spreadsheetView.setOrigin(row - 1, spreadsheetView.getOriginCol());
            }
        } else if ("View down".equals(e.getActionCommand())) {
            final int row = spreadsheetView.getOriginRow();
            spreadsheetView.setOrigin(row + 1, spreadsheetView.getOriginCol());
        } else if ("View left".equals(e.getActionCommand())) {
            final int col = spreadsheetView.getOriginCol();
            if (col > 1) {
                spreadsheetView.setOrigin(spreadsheetView.getOriginRow(), col - 1);
            }
        } else if ("View right".equals(e.getActionCommand())) {
            final int col = spreadsheetView.getOriginCol();
            spreadsheetView.setOrigin(spreadsheetView.getOriginRow(), col + 1);
        }
    } 
}
