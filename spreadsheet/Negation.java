package spreadsheet;

/**
 * A negation (e.g., -5, or -x).
 */
public class Negation extends UnaryOperation {
        
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public Negation(final Node child) {
        super(child);
    }

    @Override
    public String toString() {
        return "(-" + childToString() + ")";
    }
    
    @Override
    public double computeUnary(final CellValue child) {
        return -child.asNumber();
    } 
}