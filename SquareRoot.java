/**
 * An sqare root computation, (ex. sqrt(9) = 3).
 */
public class SquareRoot extends UnaryOperation {
    
    
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public SquareRoot(final Node child) {
        super(child);
    }
    
    @Override
    public String toString() {
        return "sqrt(" + childToString() + ")";
    }
    
    @Override
    public double computeUnary(final CellValue child) {
        return Math.sqrt(child.asNumber());
    }
}
