package spreadsheet;

/**
 * An division.
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
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
    public CellValue computeBinaryFromNumbers(final double left, final double right) {
        if (right == 0.0) {
            return new ErrorCellValue("#DIV/0!", "Division by zero.");
        } else {
            return new NumberCellValue(left / right);
        }
    }
}
