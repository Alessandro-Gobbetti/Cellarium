package spreadsheet;

/**
 * An integer subtraction.
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
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
    public CellValue computeBinaryFromNumbers(final double left, final double right) {
        return new NumberCellValue(left - right);
    }
}
