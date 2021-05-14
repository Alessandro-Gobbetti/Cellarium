package parser;

/**
 * An integer multiplication.
 */
public class Multiplication extends BinaryOperation {

    /**
     * Create a new IntMultiplication node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public Multiplication(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }
    
    @Override
    public String getOp() {
        return "*";
    }
    
    @Override
    public double computeBinary(final double left, final double right) {
        return left * right;
    }
}

