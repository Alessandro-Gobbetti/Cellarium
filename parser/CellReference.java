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
     * Constructor for objects of class CellReference
     */
    public CellReference(boolean rowIsConstant, int row, boolean colIsConstant, int col) {
        this.rowIsConstant = rowIsConstant;
        this.row = row;
        this.colIsConstant = colIsConstant;
        this.col = col;
    }

    public int getRow(int baseRow) {
        return rowIsConstant ? row : baseRow + row;
    }
    
    public int getCol(int baseCol) {
        return colIsConstant ? col : baseCol + col;
    }
    
    //parse cell reference
}
