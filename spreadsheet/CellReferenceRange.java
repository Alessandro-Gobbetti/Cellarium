package spreadsheet;

import java.util.ArrayList;


/**
 * Defines a range of Cells.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class CellReferenceRange extends Node {
    
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
    public CellValue eval() {
        final int beginRow = getMinRow();
        final int beginCol = getMinCol();
        final int endRow = getMaxRow();
        final int endCol = getMaxCol();
        if (beginRow == endRow && beginCol == endCol) {
            return begin.eval();
        } else { 
            return new ErrorCellValue("Err:Syntax", "Cannot evaluate a range");
        }
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
    public void addDependencies(final ArrayList<Cell> list) {
        final int beginRow = getMinRow();
        final int beginCol = getMinCol();
        final int endRow = getMaxRow();
        final int endCol = getMaxCol();
        
        for (int row = beginRow; row <= endRow; row++) {
            for (int col = beginCol; col <= endCol; col++) {
                final Cell cell = getSpreadsheet().getOrCreate(row,col);
                if (!list.contains(cell)) {
                    list.add(cell);
                }
            }
        }
    }
    
    /**
     * To get the spreadsheet connected to this cells.
     * 
     * @return the spreadsheet connected to this cells.
     */
    public Spreadsheet getSpreadsheet() {
        return begin.getSpreadsheet();
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
