package parser;

/**
 * An integer subtraction.
 */
public class Subtraction extends BinaryOperation {
        
    /**
     * Create a new IntSubtraction node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public Subtraction(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }
    
    @Override
    public Instruction getInstruction() {
        return new SUB();
    }
    
    @Override
    public String getOp() {
        return "-";
    }
    
    public double eval() {
        return 0;
    }
}
