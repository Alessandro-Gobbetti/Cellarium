package parser;

/**
 * An integer addition.
 */
public class Addition extends BinaryOperation {
    
    /**
     * Create a new IntAddition node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public Addition(final Node leftChild, final Node rightChild) {
        super(leftChild,rightChild);
    }
    
    @Override
    public String getOp() {
        return "+";
    }
    
    @Override
    public double computeBinary(final double left, final double right) {
        return left + right;
    }
}
