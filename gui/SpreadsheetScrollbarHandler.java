package gui;

import spreadsheet.*;
import java.awt.Scrollbar;

import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;


/**
 * Write a description of class Events here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetScrollbarHandler implements AdjustmentListener {
    
    private Spreadsheet spreadsheet;
    private SpreadsheetViewTableModel spreadsheetView;
    private Scrollbar scrollbar;
    private Table table;
    
    /**
     * Constrctor of Events.
     * @param spreadsheet      the spreadsheet to compute on.
     * @param spreadsheetView  the SpreadsheetViewTableModel reference object.
     */
    public SpreadsheetScrollbarHandler(final Spreadsheet spreadsheet, final SpreadsheetViewTableModel spreadsheetView,
                                       final Scrollbar scrollbar, final Table table) {
        this.spreadsheet = spreadsheet;
        this.spreadsheetView = spreadsheetView;
        this.scrollbar = scrollbar;
        this.table = table;
    }
    
    private boolean isVertical() {
        return scrollbar.getOrientation() == Scrollbar.VERTICAL;
    }
    
    private void setViewOrigin(final int origin) {
        if (isVertical()) {
            spreadsheetView.setOrigin(origin, spreadsheetView.getOriginCol());
        } else {
            spreadsheetView.setOrigin(spreadsheetView.getOriginRow(), origin);
        }
    }
    
    private int getViewOrigin() {
        return isVertical() ? spreadsheetView.getOriginRow() : spreadsheetView.getOriginCol();
    }
    
    private int getViewVisibleCount() {
        return isVertical() ? table.getNumberOfVisibleRows() : table.getNumberOfVisibleCols();
    }
    
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        System.out.println(e);
        final int origin = e.getValue();
        setViewOrigin(origin);
        if(!e.getValueIsAdjusting()) {
            setMaximum(origin);
        }
    }
    
    public void init() {
        final int value = getViewOrigin();
        final int visible = getViewVisibleCount();
        final int minimum = 1;
        final int maximum = value + visible + visible;
        scrollbar.setValues(value, visible, minimum, maximum);
    }
    
    public void setMaximum(int value) {
        int visible = scrollbar.getVisibleAmount();
        int minimum = 1;
        int maximum = scrollbar.getMaximum();
        System.out.println(value + " " + visible +  " " + maximum);
        if (value + visible >= maximum) {
            maximum = maximum + visible;
        } else if (value + visible + visible < maximum) {
            maximum = value + visible + visible;
        }
        System.out.println(maximum);
        scrollbar.setMaximum(maximum);
    }
}
