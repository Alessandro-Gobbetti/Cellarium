/**
 * An abstract syntax tree (AST) node.
 */
public abstract interface Node {
    
    /**
     * Get the type of values produced by this node.
     * @return the type of this node
     */
    public abstract Type getType();
    
    /**
     * Get whether this node always evaluates to the exact same value.
     * @return whether this node produces a constant value
     */
    public abstract boolean isConstant();
    
    /**
     * True if the Node is an error.
     * @return true if the Node is an error
     */
    public abstract boolean isError();
    
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
    public abstract CellValue eval();
}
