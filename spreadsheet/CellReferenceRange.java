package spreadsheet;

import java.util.ArrayList;


/**
 * Write a description of class CellReferenceRange here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class CellReferenceRange extends Node {
    // instance variables - replace the example below with your own
    private CellReference begin;
    private CellReference end;

    /**
     * Constructor for objects of class CellReferenceRange.
     * @param begin the first cell
     * @param end the last cell
     */
    public CellReferenceRange(final CellReference begin, final CellReference end) {
        super();
        this.begin = begin;
        this.end = end;
    }

    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        //FIXME
        return begin.eval(spreadsheet);
    }
    
    @Override
    public boolean isConstant() {
        return false;
    }
    
    @Override
    public String toString() {
        return begin.toString() + ":" + end.toString();
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        final int beginRow = getMinRow();
        final int beginCol = getMinCol();
        final int endRow = getMaxRow();
        final int endCol = getMaxCol();
        
        for (int row = beginRow; row <= endRow; row++) {
            for (int col = beginCol; col <= endCol; col++) {
                final Cell cell = spreadsheet.getOrCreate(row,col);
                if (!list.contains(cell)) {
                    list.add(cell);
                }
            }
        }
    }
    
    /**
     * to get the minimum row in the range.
     * 
     * @return the minimum row in the range.
     */
    public int getMinRow() {
        final int beginRow = begin.getRow(0);
        final int endRow = end.getRow(0);
        return beginRow < endRow ? beginRow : endRow;
    }
    
    /**
     * to get the maximum row in the range.
     * 
     * @return the maximum row in the range.
     */
    public int getMaxRow() {
        final int beginRow = begin.getRow(0);
        final int endRow = end.getRow(0);
        return beginRow > endRow ? beginRow : endRow;
    }
    
    /**
     * to get the minimum column in the range.
     * 
     * @return the minimum column in the range.
     */
    public int getMinCol() {
        final int beginCol = begin.getCol(0);
        final int endCol = end.getCol(0);
        return beginCol < endCol ? beginCol : endCol;
    }
    
    /**
     * to get the maximum column in the range.
     * 
     * @return the maximum column in the range.
     */
    public int getMaxCol() {
        final int beginCol = begin.getCol(0);
        final int endCol = end.getCol(0);
        return beginCol > endCol ? beginCol : endCol;
    }
        
}
