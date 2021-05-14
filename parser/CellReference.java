package parser;

/**
 * A Cell Reference
 *
 * @author Alessandro Gobbetti
 * @version 2021.05.12
 */
public class CellReference {
    private boolean rowIsConstant;
    private int row;
    private boolean colIsConstant;
    private int col;

    /**
     * Constructor for CellReference.
     * @param rowIsConstant true if the row is constant: i.e. A$1
     * @param row the row to look at
     * @param colIsConstant true if the column is constant: i.e. $A1 
     * @param col the column to look at
     */
    public CellReference(final boolean rowIsConstant, final int row, final boolean colIsConstant, final int col) {
        this.rowIsConstant = rowIsConstant;
        this.row = row;
        this.colIsConstant = colIsConstant;
        this.col = col;
    }

    /**
     * To get the reference row.
     * @param baseRow the row of the given cell
     * @return the row we are looking for
     */
    public int getRow(final int baseRow) {
        return rowIsConstant ? row : baseRow + row;
    }
    
    /**
     * To get the reference column.
     * @param baseCol the column of the given cell
     * @return the column we are looking for
     */
    public int getCol(final int baseCol) {
        return colIsConstant ? col : baseCol + col;
    }
    
    //parse cell reference
}
