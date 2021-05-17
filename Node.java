import java.util.ArrayList;

/**
 * An abstract syntax tree (AST) node.
 */
public abstract class Node {
    
    /**
     * Get whether this node always evaluates to the exact same value.
     * @return whether this node produces a constant value
     */
    public abstract boolean isConstant();
    
    /**
     * True if the Node is an error.
     * By default there is no error, the subclasses that introduces errors must override.
     * @return true if the Node is an error
     */
    public boolean isError() {
        return false;
    }
    
    /**
     * Decompile this node into a string.
     * Note that the resulting string may have
     * extra parentheses.
     * @return a String representation of this AST
     */
    public abstract String toString();
    
    /**
     * Evaluates a certain computation of a Node.
     * @return a double, the result of the computation
     */
    public abstract CellValue eval(final Spreadsheet spreadsheet);
    
    /**
     * Changes the Dependecies.
     * By default there are no dependencies, the subclasses that introduces dependencies must override.
     * @param  spreadsheet  the spreadsheet in which the cell is situated.
     * @param  list  the ArrayList with the current dependecies.
     */
    public abstract void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list);
    
    /**
     * Returns type of the Cell.
     * @param  spreadsheet  the spreadsheet in which the cell is situated.
     * @return the new ArrayList with the Cells who depend from this one.
     */
    public ArrayList<Cell> dependencies(final Spreadsheet spreadsheet) {
        final ArrayList<Cell> result = new ArrayList<Cell>();
        addDependencies(spreadsheet, result);
        return result;
    }
}
