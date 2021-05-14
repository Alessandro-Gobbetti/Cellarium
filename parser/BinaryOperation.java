package parser;

/**
 * A Binary Operation.
 */
public abstract class BinaryOperation implements Node {
    
    private final Node leftChild;
    private final Node rightChild;

    
    /**
     * Create a new BinaryOperations node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public BinaryOperation(final Node leftChild, final Node rightChild) {
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public boolean isConstant() {
        return leftChild.isConstant() && rightChild.isConstant();
    }
    
    @Override
    public Type getType() {
        return Type.DOUBLE;
    }
    
    /**
     * Get the string representation of the operator.
     * @return the string representation of the operator
     */
    public abstract String getOp();
    
    @Override
    public String toString() {
        return "(" + leftChild.toString() + getOp() + rightChild.toString() + ")";
    }
    
    /**
     * To compute the result of a binary operation.
     * @param left  the left operand
     * @param right  the right operand
     * @return the result of a binary operation
     */
    public abstract double computeBinary(final double left, final double right);
    
    @Override
    public double eval() {
        return computeBinary(leftChild.eval(), rightChild.eval());
    }
}
