package spreadsheet;

/**
 * A assignment (e.g., =5, or =x).
 */
public class Assign extends UnaryOperation {
        
    /**
     * Create a new Assign node.
     * @param child the operand we will negate
     */
    public Assign(final Node child) {
        super(child);
    }

    @Override
    public String toString() {
        return "= " + childToString();
    }
    
    @Override
    public CellValue computeUnary(final CellValue child) {
        return child;
    }
    
    @Override
    public CellValue computeUnaryFromNumber(final double value) {
        return new NumberCellValue(value);
    } 
}
