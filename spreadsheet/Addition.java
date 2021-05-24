package spreadsheet;

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
    public CellValue computeBinaryFromNumbers(final double left, final double right) {
        return new NumberCellValue(left + right);
    }
}
