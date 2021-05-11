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
    
    /**
     * Get the instruction implementing the operator.
     * @return the instruction implementing the operator.
     */
    public abstract Instruction getInstruction();
    
    @Override
    public void compile(final Program p) {
        child.compile(p);
        p.append(getInstruction());
    }

    @Override
    public String toString() {
        return "(" + child.toString() + ")";
    }
    
    public abstract double compute(Node child);
    
    @Override
    public double eval() {
        return compute(child);
    }
}
