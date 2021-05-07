/**
 * An abstract syntax tree (AST) node.
 */
public class Node {
    
    /**
     * Get the type of values produced by this node.
     * @return the type of this node
     */
    public Type getType() {
        // implemented in subclasses
        return Type.INVALID;
    }
    
    /**
     * Get whether this node always evaluates to the exact same value.
     * @return whether this node produces a constant value
     */
    public boolean isConstant() {
        // implemented in subclasses
        return true;
    }
    
    /**
     * Compile this node into the given Program.
     * @param p The program to append this node to
     */
    public void compile(final Program p) {
        // implemented in subclasses
    }

    /**
     * Decompile this node into a string.
     * Note that the resulting string may have
     * extra parentheses.
     * @return a String representation of this AST
     */
    public String toString() {
        // implemented in subclasses
        return "?";
    }
    
}
