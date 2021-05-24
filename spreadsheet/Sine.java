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
    public double computeUnary(final CellValue child) {
        return Math.sin(child.asNumber());
    }
}
