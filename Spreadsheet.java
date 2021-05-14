import java.util.*;

/**
 * Write a description of class Spreadsheet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Spreadsheet {
    
    private HashMap<Integer,Cell> cellMap;
    private int maxUsedCellRow;
    private int maxUsedCellCol; 
    
    private final static int MaxDim = 32768;
    
    public Spreadsheet() {
        cellMap = new HashMap<Integer,Cell>();
        maxUsedCellRow = 0;
        maxUsedCellCol = 0;
    }
        
    public int indexFromRowCol(final int row, final int col) {
        return row * MaxDim + col;
    }
    
    public int colFromIndex(final int index) {
        return index % MaxDim;
    }
    
    public int rowFromIndex(final int index) {
        return (index - colFromIndex(index)) / MaxDim;
    }
    
    public void remove(final int row, final int col) {
        final int cellIndex = indexFromRowCol(row, col);
        cellMap.remove(cellIndex);
        // Update max indices FIXME
        if (row == maxUsedCellRow) {
            boolean maxUsedRowUpToDate = false;
            int resultRow = 0;
            for (int r = maxUsedCellRow; r>=0; --r) {                                                                  
                for (int c = maxUsedCellCol; c>=0; --c) {
                    if (exists(r,c)) {
                        resultRow = r;
                        maxUsedRowUpToDate = true;
                        break;
                    }
                }
                if (maxUsedRowUpToDate) {
                    break;
                }
            }
            maxUsedCellRow = resultRow;
        }
            
        if (col == maxUsedCellCol) {
            boolean maxUsedColUpToDate = false;
            int resultCol = 0;
            for (int r = maxUsedCellRow; r>=0; --r) {                                                                  
                for (int c = maxUsedCellCol; c>=0; --c) {
                    if (exists(r,c)) {
                        resultCol = c;
                        maxUsedColUpToDate = true;
                        break;
                    }
                }
                if (maxUsedColUpToDate) {
                    break;
                }
            }
            maxUsedCellCol = resultCol;
        }
    }
    
    
    
    public boolean exists(final int row,final int col) {
        final int cellIndex = indexFromRowCol(row, col);
        return cellMap.containsKey(cellIndex);
    }
    
    public Cell get(final int row, final int col) {
        assert row >= 0;
        assert col >= 0;
        assert row < MaxDim;
        assert col < MaxDim;
        final int cellIndex = indexFromRowCol(row, col);
        return cellMap.get(cellIndex);
    }
    
    public Cell getOrCreate(final int row, final int col) {
        assert row >= 0;
        assert col >= 0;
        assert row < MaxDim;
        assert col < MaxDim;
        Cell result = get(row, col);
        if (result == null) {
            final int cellIndex = indexFromRowCol(row, col);
            result = new Cell(this, row, col);
            cellMap.put(cellIndex, result);
            // Update max indices
            maxUsedCellRow = Math.max(maxUsedCellRow, row);
            maxUsedCellCol = Math.max(maxUsedCellCol, col);
        }
        return result;
    }
    
    public CellValue getValue(final int row, final int col) {
        final Cell c = getOrCreate(row, col);
        return c.eval();
    }
    
    public void copyPaste(final int srcRow, final int srcCol, final int dstRow, final int dstCol) {
        if (!exists(srcRow, srcCol)) {
            remove(dstRow, dstCol);
        } else {
            // FIXME
        }
    }
    
    public void cutPaste(final int srcRow, final int srcCol, final int dstRow, final int dstCol) {
        // FIXME
        copyPaste(srcRow, srcCol, dstRow, dstCol);
        remove(srcRow, srcCol);
    }
    
    
    public void print() {
        for (int row = 0; row <= maxUsedCellRow; row++) {
            for (int col = 0; col <= maxUsedCellCol; col++) {
                System.out.print(exists(row, col) ? getValue(row,col).asString() : ", ");
            }
            System.out.println();
        }
    }
    
    public void test() {
        final Cell c11 =  getOrCreate(1,1);
        final Cell c21 =  getOrCreate(2,1);
        final Cell c1030 =  getOrCreate(10,30);
        c11.setFormula(new ConstFormula(10));
        c21.setFormula(new ScalingFormula(2, 1, 1));
        c1030.setFormula(new ScalingFormula(3, 2, 1));
    }
    
}