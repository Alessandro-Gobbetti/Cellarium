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
    
    private final static int maxDim = 16384;
    
    public Spreadsheet() {
        cellMap = new HashMap<Integer,Cell>();
        maxUsedCellRow = 0;
        maxUsedCellCol = 0;
    }
        
    public int indexFromRowCol(int row, int col) {
        return row * maxDim + col;
    }
    
    public int ColFromIndex(int index) {
        return index % maxDim;
    }
    
    public int RowFromIndex(int index) {
        return index - ColFromIndex(index);
    }
    
    public void remove(int row, int col) {
        int cellIndex = indexFromRowCol(row, col);
        cellMap.remove(cellIndex);
        // Update max indices FIXME
        if (row == maxUsedCellRow) {
            boolean maxUsedRowUpToDate = false;
            int resultRow = 0;
            for (int r = maxUsedCellRow; r>=0; --r) {                                                                  
                for (int c = maxUsedCellCol; c>=0; --c) {
                    if (!isEmpty(r,c)) {
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
                    if (!isEmpty(r,c)) {
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
    
    public boolean isEmpty(int row, int col) {
        int cellIndex = indexFromRowCol(row, col);
        return !cellMap.containsKey(cellIndex);
    }
    
    public Cell get(int row, int col) {
        assert(row >= 0);
        assert(col >= 0);
        assert(row < maxDim);
        assert(col < maxDim);
        int cellIndex = indexFromRowCol(row, col);
        return cellMap.get(cellIndex);
    }
    
    public Cell getOrCreate(int row, int col) {
        assert(row >= 0);
        assert(col >= 0);
        assert(row < maxDim);
        assert(col < maxDim);
        Cell result = get(row, col);
        if (result == null) {
            int cellIndex = indexFromRowCol(row, col);
            result = new Cell(this, row, col);
            cellMap.put(cellIndex, result);
            // Update max indices
            maxUsedCellRow = Math.max(maxUsedCellRow, row);
            maxUsedCellCol = Math.max(maxUsedCellCol, col);
        }
        return result;
    }
    
    public CellValue getValue(int row, int col) {
        Cell c = getOrCreate(row, col);
        return c.eval();
    }
    
    public void copyPaste(int srcRow, int srcCol, int dstRow, int dstCol) {
        if (isEmpty(srcRow, srcCol)) {
            remove(dstRow, dstCol);
        } else {
            // FIXME
        }
    }
    
    public void cutPaste(int srcRow, int srcCol, int dstRow, int dstCol) {
        // FIXME
        copyPaste(srcRow, srcCol, dstRow, dstCol);
        remove(srcRow, srcCol);
    }
}
    
    
    
    
    
    
    
    
    // private Map<Integer,Cell> variables;
    
    // // FIXME: ArrayList<ArrayList<Cell>>
    // private Cell[][] sheet;
    // private ArrayList<ArrayList<Cell>> s;
    
    // ArrayList<ArrayList<Cell>> d = new ArrayList<ArrayList<Cell>>();
    
    
    
    // private int indexToInt(int row, int col) {
        // return row * maxDimension + col;
    // }
    
    // private void set(Cell c, int row, int col) {
        // int index = indexToInt(row, col);
        // variables.put(index, c);
    // }
        
    
    // private void setValue(int column, int row, double value, String s) {
        // Cell c = d.get(row).get(column);
        // c.setValue(value);
    // }
    
    // // public static void setValue(ArrayList<ArrayList<Cell>> list, int row, int column, Cell value){
     // // list.get(row).set(column, value);
    // // }

    // /**
     // * Constructor for objects of class Spreadsheet
     // */
    // public Spreadsheet() {
        // this.sheet = new Cell[100][100];
    // }

    // /**
     // * An example of a method - replace this comment with your own
     // *
     // * @param  y  a sample parameter for a method
     // * @return    the sum of x and y
     // */
    // public Cell getCell(int y, int x){
        // return sheet[y][x];        
    // }
