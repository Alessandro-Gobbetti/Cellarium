package spreadsheet;

/**
 * A tangent Node.
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class Tangent extends UnaryOperation {
    
    
    /**
     * Create a new tan node.
     * @param child the operand we will negate
     */
    public Tangent(final Node child) {
        super(child);
    }
    
    @Override
    public String toString() {
        return "tan(" + childToString() + ")";
    }
    
    @Override
    public CellValue computeUnaryFromNumber(final double value) {
        return new NumberCellValue(Math.tan(value));
    } 
}
