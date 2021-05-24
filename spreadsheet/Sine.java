package spreadsheet;

/**
 * A sin operation.
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
