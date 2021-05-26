package gui;

import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * To handle a scrollbar.
 *
 * @author Alessandro Gobbett & Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetScrollbarHandler implements AdjustmentListener {
    
    private SpreadsheetViewTableModel spreadsheetView;
    private Scrollbar scrollbar;
    private Table table;
    
    /**
     * Constrctor of SpreadsheetScrollbarHandler.
     * 
     * @param spreadsheetView  the SpreadsheetViewTableModel reference object.
     * @param scrollbar        the scrollbar to handle
     * @param table            the Table to refer the scrollbar
     */
    public SpreadsheetScrollbarHandler(final SpreadsheetViewTableModel spreadsheetView,
                                       final Scrollbar scrollbar,
                                       final Table table) {
        this.spreadsheetView = spreadsheetView;
        this.scrollbar = scrollbar;
        this.table = table;
    }
    
    /**
     * Return true if the scrollbar is vertical, false otherwise.
     * 
     * @return true if the scrollbar is vertical, false otherwise.
     */
    private boolean isVertical() {
        return scrollbar.getOrientation() == Scrollbar.VERTICAL;
    }
    
    @Override
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        System.out.println(e);
        final int origin = e.getValue();
        setViewOrigin(origin);
        if (!e.getValueIsAdjusting()) {
            setMaximum(origin);
        }
    }
    
    /**
     * To init a scrollbar.
     */
    public void init() {
        final int value = getViewOrigin();
        final int visible = getViewVisibleCount();
        final int minimum = 1;
        final int maximum = value + visible + visible;
        scrollbar.setValues(value, visible, minimum, maximum);
    }
    
    /**
     * To compute the new  maximum value of a scrollbar given a value.
     * This method allow to resize the scrollbar when we reach the end.
     * 
     * @param value the curren value of the bar.
     */
    public void setMaximum(final int value) {
        final int visible = scrollbar.getVisibleAmount();
        int maximum = scrollbar.getMaximum();
        if (value + visible >= maximum) {
            maximum = maximum + visible;
        } else if (value + visible + visible < maximum) {
            maximum = value + visible + visible;
        }
        scrollbar.setMaximum(maximum);
    }
    
    /**
     * to move a spreadsheet following the scrollbar.
     * 
     * @param origin the new origin.
     */
    private void setViewOrigin(final int origin) {
        if (isVertical()) {
            spreadsheetView.setOrigin(origin, spreadsheetView.getOriginCol());
        } else {
            spreadsheetView.setOrigin(spreadsheetView.getOriginRow(), origin);
        }
    }
    
    /**
     * To get the origin of the spreadsheet.
     * @return the origin row if the scrollbar is vertical, the origin column otherwise.
     */
    private int getViewOrigin() {
        return isVertical() ? spreadsheetView.getOriginRow() : spreadsheetView.getOriginCol();
    }
    
    /**
     * To get the visible count of cells:
     * vertically or horizontally depending on the scrollbar orientation.
     * 
     * @return the visible count of cells:
     *         vertically or horizontally depending on the scrollbar orientation.
     */
    private int getViewVisibleCount() {
        return isVertical() ? table.getNumberOfVisibleRows() : table.getNumberOfVisibleCols();
    }
    
}
