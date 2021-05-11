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
    public Instruction getInstruction() {
        return new ADD();
    }
    
    @Override
    public String getOp() {
        return "+";
    }
    
    @Override
    public double evalBinary (Node leftChild,Node rightChild) {
        return leftChild.eval() + rightChild.eval();
    }

}
