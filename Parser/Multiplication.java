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
    public Instruction getInstruction() {
        return new IMUL();
    }
    
    @Override
    public String getOp() {
        return "*";
    }
}
