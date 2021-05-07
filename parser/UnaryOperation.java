package parser;

/**
 * An integer negation (e.g., -5, or -x).
 */
public class UnaryOperation extends Node {
    
    private final Node child;
    
    
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public UnaryOperation(final Node child) {
        super();
        this.child = child;
    }
    
    
    /**
     * to get the child node
     * @return the child node
     * 
     */
    protected Node getChild() {
        return child;
    }
    
    @Override
    public boolean isConstant() {
        return child.isConstant();
    }
    
    @Override
    public Type getType() {
        return Type.DOUBLE;
    }
    
    @Override
    public void compile(final Program p) {
        child.compile(p);
        // implemented in subclasses
    }

    @Override
    public String toString() {
        return "(" + child.toString() + ")";
    }
    
}
