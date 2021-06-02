package spreadsheet;

/**
 * A logarithm Node.
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class Logarithm extends UnaryOperation {
    
    
    /**
     * Create a new tan node.
     * @param child the operand we will negate
     */
    public Logarithm(final Node child) {
        super(child);
    }
    
    @Override
    public String toString() {
        return "log(" + childToString() + ")";
    }
    
    @Override
    public CellValue computeUnaryFromNumber(final double value) {
        return new NumberCellValue(Math.log(value));
    } 
}
