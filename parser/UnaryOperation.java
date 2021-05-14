package parser;

/**
 * A Unary Operation.
 */
public abstract class UnaryOperation implements Node {
    
    private final Node child;
    
    
    /**
     * Create a new UnaryOperation node.
     * @param child the operand
     */
    public UnaryOperation(final Node child) {
        super();
        this.child = child;
    }
    
    /**
     * To get the child node.
     * @return the child node
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
    public String toString() {
        return "(" + child.toString() + ")";
    }
    
    /**
     * Returns the child as a String.
     * @return the child as a String 
     */
    public String childToString() {
        return child.toString();
    }
    
    /**
     * To compute the result of a unary operation.
     * @param child  the operand
     * @return the result of a unary operation
     */
    public abstract double computeUnary(final double child);
    
    @Override
    public double eval() {
        return computeUnary(child.eval());
    }
}
