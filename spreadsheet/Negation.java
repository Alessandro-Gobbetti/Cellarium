package spreadsheet;

/**
 * A negation (e.g., -5, or -x).
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
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
    public CellValue computeUnaryFromNumber(final double value) {
        return new NumberCellValue(-value);
    } 
}