package parser;

/**
 * An integer division.
 */
public class Division extends BinaryOperation {

    /**
     * Create a new IntDivision node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public Division(final Node leftChild, final Node rightChild) {
        super(leftChild, rightChild);
    }
    
    @Override
    public Instruction getInstruction() {
        return new DIV();
    }
    
    @Override
    public String getOp() {
        return "/";
    }
    
    public double compute (Node leftChild,Node rightChild) {
        return leftChild.eval() / rightChild.eval();
    }
}