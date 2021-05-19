import java.util.*;
import java.io.PrintWriter;
import java.io.File;

/**
 * Write a description of class Spreadsheet here.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public class Spreadsheet {
    
    private HashMap<Integer,Cell> cellMap;
    private int maxUsedCellRow;
    private int maxUsedCellCol; 
    
    private static final int MaxDim = 32768;
    
    /**
     * Constructor of class Spreadsheet. 
     * Sets all the fields.
     */
    public Spreadsheet() {
        cellMap = new HashMap<Integer,Cell>();
        maxUsedCellRow = 0;
        maxUsedCellCol = 0;
    }
    
    /**
     * Returns type of the Cell.
     * @param  row  the row of the Cell.
     * @param  col  the column of the Cell.
     * @return the index, which is unique for every Cell in a Spreadsheet. 
     */
    public int indexFromRowCol(final int row, final int col) {
        return row * MaxDim + col;
    }
    
    /**
     * Calculates the column of the Cell.
     * @param  index  the index of the Cell.
     * @return the column of the Cell. 
     */
    public int colFromIndex(final int index) {
        return index % MaxDim;
    }
    
    /**
     * Calculates the row of the Cell.
     * @param  index  the index of the Cell.
     * @return the row of the Cell. 
     */
    public int rowFromIndex(final int index) {
        return (index - colFromIndex(index)) / MaxDim;
    }
    
    public int getMaxUsedCellRow() {
        return maxUsedCellRow;
    }
    
    public int getMaxUsedCellCol() {
        return maxUsedCellCol;
    }
    
    /**
     * Removes a certain Cell.
     * @param  row  the row of the Cell.
     * @param  col  the column of the Cell.
     */
    public void remove(final int row, final int col) {
        final int cellIndex = indexFromRowCol(row, col);
        cellMap.remove(cellIndex);
        // Update max indices
        if (row == maxUsedCellRow) {
            int resultRow = 0;
            for (int r = maxUsedCellRow; r >= 0 && resultRow == 0; --r) {                                                                  
                for (int c = maxUsedCellCol; c >= 0 && resultRow == 0; --c) {
                    if (exists(r,c)) {
                        resultRow = r;
                    }
                }
            }
            maxUsedCellRow = resultRow;
        }
        
        if (col == maxUsedCellCol) {
            int resultCol = 0;
            for (int c = maxUsedCellCol; c >= 0 && resultCol == 0; --c) {
                for (int r = maxUsedCellRow; r >= 0 && resultCol == 0; --r) {
                    if (exists(r,c)) {
                        resultCol = c;
                    }
                }
            }
            maxUsedCellCol = resultCol;
        }
    }
    
    /**
     * Clear the spreadheet.
     */
    public void clear() {
        cellMap.clear();
        maxUsedCellRow = 0;
        maxUsedCellCol = 0;
    }
    
    
    /**
     * Removes a certain Cell.
     * @param  row  the row of the Cell.
     * @param  col  the column of the Cell.
     * @return boolean  if the Cell exists.
     */
    public boolean exists(final int row, final int col) {
        final int cellIndex = indexFromRowCol(row, col);
        return cellMap.containsKey(cellIndex);
    }
    
    /**
     * Returns the searched Cell.
     * @param  row  the row of the Cell.
     * @param  col  the column of the Cell.
     * @return the Cell.
     */
    public Cell get(final int row, final int col) {
        assert row >= 0;
        assert col >= 0;
        assert row < MaxDim;
        assert col < MaxDim;
        final int cellIndex = indexFromRowCol(row, col);
        return cellMap.get(cellIndex);
    }
    
    /**
     * Creates a Cell if thre is none. Else it returns the given one.
     * @param  row  the row of the Cell.
     * @param  col  the column of the Cell.
     * @return returns the Cell.
     */
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
    
    /**
     * Calculates the value of a Cell.
     * @param  row  the row of the Cell.
     * @param  col  the column of the Cell.
     * @return the value of a Cell.
     */
    public CellValue getValue(final int row, final int col) {
        final Cell c = getOrCreate(row, col);
        return c.eval();
    }
    
    public String getFormula(final int row, final int col) {
        final Cell c = getOrCreate(row, col);
        return c.getFormula();
    }
    
    /**
     * Creates a new Cell with the Properties of an old one.
     * @param srcRow  row of the Copied Cell.
     * @param srcCol  column of the Copied Cell.
     * @param dstRow  row of the new Cell.
     * @param dstCol  column of the new Cell.
     */
    public void copyPaste(final int srcRow, final int srcCol, final int dstRow, final int dstCol) {
        if (!exists(srcRow, srcCol)) {
            remove(dstRow, dstCol);
        } else {
            // FIXME
        }
    }
    
    /**
     * Removes a Cell and creates a new Cell with the Properties of an old one.
     * @param srcRow  row of the Copied Cell.
     * @param srcCol  column of the Copied Cell.
     * @param dstRow  row of the new Cell.
     * @param dstCol  column of the new Cell.
     */
    public void cutPaste(final int srcRow, final int srcCol, final int dstRow, final int dstCol) {
        // FIXME
        copyPaste(srcRow, srcCol, dstRow, dstCol);
        remove(srcRow, srcCol);
    }

    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Prints the current Spreadsheet.
     */
    public void print() {
        //print top border
        System.out.print("┌───────┬");
        for (int col = 0; col < maxUsedCellCol; col++) {
            System.out.print("───────┬");
        }
        System.out.println("───────┐");
        //print column names
        System.out.print("│\t");
        for (int col = 0; col <= maxUsedCellCol; col++) {
            System.out.print("│   " + ANSI_RED + CellReference.toAlpha26(col) + "\t" + ANSI_RESET );
        }
        System.out.println("│");
        //print content
        for (int row = 0; row <= maxUsedCellRow; row++) {
            System.out.print("│" + ANSI_RED + (row + 1) + "\t" + ANSI_RESET);
            for (int col = 0; col <= maxUsedCellCol; col++) {
                System.out.print("│");
                if (exists(row, col)) {
                    System.out.print(getValue(row,col).asString());    
                }
                System.out.print("\t");
            }
            System.out.println("│");
        }
        //print botton border
        System.out.print("└───────┴");
        for (int col = 0; col < maxUsedCellCol; col++) {
            System.out.print("───────┴");
        }
        System.out.println("───────┘");
    }
    
}