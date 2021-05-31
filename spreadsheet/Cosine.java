package spreadsheet;

/**
 * An integer negation (e.g., -5, or -x).
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class Cosine extends UnaryOperation {
    
    
    /**
     * Create a new Cos node.
     * @param child the operand we will negate
     */
    public Cosine(final Node child) {
        super(child);
    }
    
    @Override
    public String toString() {
        return "cos(" + childToString() + ")";
    }
    
    @Override
    public CellValue computeUnaryFromNumber(final double value) {
        return new NumberCellValue(Math.cos(value));
    } 
}