/**
 * A Binary Operation.
 */
public class BinaryOperation extends Node {
    
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
        return Type.INT;
    }
    
    /**
     * Get the string representation of the operator.
     * @return the string representation of the operator
     */
    public String getOp() {
        // implemented in subclasses
        return "?";
    }
    
    /**
     * Get the instruction implementing the operator.
     * @return the instruction implementing the operator.
     */
    public Instruction getInstruction() {
        // implemented in subclasses
        return null;
    }
    
    @Override
    public void compile(final Program p) {
        leftChild.compile(p);
        rightChild.compile(p);
        p.append(getInstruction());
    }
    
    @Override
    public String toString() {
        return "(" + leftChild.toString() + getOp() + rightChild.toString() + ")";
    }
}
