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
    public String getOp() {
        return "/";
    }
    
    @Override
    public double computeBinary(final CellValue left, final CellValue right) {
        return left.asNumber() / right.asNumber();
    }
}
