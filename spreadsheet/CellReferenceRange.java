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
        this.begin = begin;
        this.end = end;
    }

    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        //fixme
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
        int beginRow = getMinRow();
        int beginCol = getMinCol();
        int endRow = getMaxRow();
        int endCol = getMaxCol();
        
        for (int row = beginRow; row <= endRow; row++) {
            for (int col = beginCol; col <= endCol; col++) {
                final Cell cell = spreadsheet.getOrCreate(row,col);
                if (!list.contains(cell)) {
                    list.add(cell);
                }
            }
        }
    }
    
    public int getMinRow() {
        int beginRow = begin.getRow(0);
        int endRow = end.getRow(0);
        return beginRow < endRow ? beginRow : endRow;
    }
    
    public int getMaxRow() {
        int beginRow = begin.getRow(0);
        int endRow = end.getRow(0);
        return beginRow > endRow ? beginRow : endRow;
    }
    
    public int getMinCol() {
        int beginCol = begin.getCol(0);
        int endCol = end.getCol(0);
        return beginCol < endCol ? beginCol : endCol;
    }
    
    public int getMaxCol() {
        int beginCol = begin.getCol(0);
        int endCol = end.getCol(0);
        return beginCol > endCol ? beginCol : endCol;
    }
        
}
