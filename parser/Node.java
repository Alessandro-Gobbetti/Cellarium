package parser;

/**
 * An abstract syntax tree (AST) node.
 */
public abstract interface Node {
    
    /**
     * Get the type of values produced by this node.
     * @return the type of this node
     */
    public Type getType();
    
    /**
     * Get whether this node always evaluates to the exact same value.
     * @return whether this node produces a constant value
     */
    public boolean isConstant();
    
    /**
     * Compile this node into the given Program.
     * @param p The program to append this node to
     */
    public void compile(final Program p);

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
    public abstract double eval();
}
