package gui;

import spreadsheet.*;

import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;


/**
 * Write a description of class Events here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Events implements AdjustmentListener{
    private Spreadsheet spreadsheet;
    private SpreadsheetViewTableModel spreadsheetView;
    
    /**
     * Constrctor of Events.
     * @param spreadsheet      the spreadsheet to compute on.
     * @param spreadsheetView  the SpreadsheetViewTableModel reference object.
     */
    public Events(final Spreadsheet spreadsheet, final SpreadsheetViewTableModel spreadsheetView) {
        this.spreadsheet = spreadsheet;
        this.spreadsheetView = spreadsheetView;
    }
    
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (AdjustmentEvent.UNIT_INCREMENT == e.getValue()) {
            final int row = spreadsheetView.getOriginRow();
            if (row > 1) {
                spreadsheetView.setOrigin(row - 1, spreadsheetView.getOriginCol());
            }
        // } else if ("View down".equals(e.getActionCommand())) {
            // final int row = spreadsheetView.getOriginRow();
            // spreadsheetView.setOrigin(row + 1, spreadsheetView.getOriginCol());
        // } else if ("View left".equals(e.getActionCommand())) {
            // final int col = spreadsheetView.getOriginCol();
            // if (col > 1) {
                // spreadsheetView.setOrigin(spreadsheetView.getOriginRow(), col - 1);
            // }
        // } else if ("View right".equals(e.getActionCommand())) {
            // final int col = spreadsheetView.getOriginCol();
            // spreadsheetView.setOrigin(spreadsheetView.getOriginRow(), col + 1);
        }
    }
}
