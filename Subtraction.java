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
    public String getOp() {
        return "-";
    }
    
    @Override
    public double computeBinary(final CellValue left, final CellValue right) {
        return left.asNumber() - right.asNumber();
    }
}
