package spreadsheet;

import java.util.HashMap;

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
    
    private static final int MAXDIM = 32768;
    
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
        return row * MAXDIM + col;
    }
    
    /**
     * Calculates the column of the Cell.
     * @param  index  the index of the Cell.
     * @return the column of the Cell. 
     */
    public int colFromIndex(final int index) {
        return index % MAXDIM;
    }
    
    /**
     * Calculates the row of the Cell.
     * @param  index  the index of the Cell.
     * @return the row of the Cell. 
     */
    public int rowFromIndex(final int index) {
        return (index - colFromIndex(index)) / MAXDIM;
    }
    
    /**
     * Returns the row of the nonepmty cell with the greatest row.
     * @return the greatest row. 
     */
    public int getMaxUsedCellRow() {
        return maxUsedCellRow;
    }
    
    /**
     * Returns the row of the nonepmty cell with the greatest column.
     * @return the greatest column. 
     */
    public int getMaxUsedCellCol() {
        return maxUsedCellCol;
    }
    
    /**
     * Removes a certain Cell.
     * @param  row  the row of the Cell.
     * @param  col  the column of the Cell.
     */
    public void remove(final int row, final int col) {
        final Cell cell = get(row, col);
        if (cell != null) {
            cell.setFormula(null);
            if (cell.numberOfCellsDependingOnThis() > 0) {
                // we cannot remove the cell because it is referenced by some formula.
                return;
            }
        }
        final int cellIndex = indexFromRowCol(row, col);
        cellMap.remove(cellIndex);
        // Update max indices
        updateMaxRow(row);
        updateMaxCol(col);
    }
    
    /**
     * To update the max row.
     * @param newRow the new row.
     */
    private void updateMaxRow(final int newRow) {
        if (newRow == maxUsedCellRow) {
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
    }

    /**
     * To update the max column.
     * @param newCol the new column.
     */
    private void updateMaxCol(final int newCol) {
        if (newCol == maxUsedCellCol) {
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
     * Whatches out if the Cell exists.
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
        assert row < MAXDIM;
        assert col < MAXDIM;
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
        assert row < MAXDIM;
        assert col < MAXDIM;
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
     * Returns the value of the Cell.
     * @param  row  the row of the Cell.
     * @param  col  the column of the Cell.
     * @return the value of a Cell.
     */
    public CellValue getValue(final int row, final int col) {
        final Cell c = getOrCreate(row, col);
        return c.eval();
    }
    
    /**
     * Returns the formula of the Cell.
     * @param row  the row of the Cell.
     * @param col  the column of the Cell.
     * @return the formula of the Cell. 
     */
    public String getFormula(final int row, final int col) {
        String result = "";
        if (exists(row, col)) {
            final Cell c = get(row, col);
            result = c.getFormula();
        }
        return result;
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
    
}