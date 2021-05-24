package spreadsheet;

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
    public CellValue computeUnaryFromNumber(final double value) {
        if (value < 0) {
            return new ErrorCellValue("#SQRT(NEG)",
                                      "Negative value for square root, got" + value);
        } else {
            return new NumberCellValue(Math.sqrt(value));
        }
    } 
    
}
