package spreadsheet;

/**
 * A sin operation.
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class Sine extends UnaryOperation {
    
    /**
     * Create a new Sin node.
     * @param child the operand we will compute the sin
     */
    public Sine(final Node child) {
        super(child);
    }

    @Override
    public String toString() {
        return "sin(" + childToString() + ")";
    }
    
    @Override
    public CellValue computeUnaryFromNumber(final double value) {
        return new NumberCellValue(Math.sin(value));
    } 
}
