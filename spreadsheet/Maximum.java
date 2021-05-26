package spreadsheet;

/**
 * A Maximum is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 */
public class Maximum extends RangeOperation {
    
    /**
     * Create a new Maximum node.
     * @param child the range over which to calculate the average.
     */
    public Maximum(final CellReferenceRange child) {
        super(child);
    }
    
    @Override
    protected boolean useOnlyNumbers() { 
        return true;
    }
    
    @Override
    protected double computeNext(final double result, final CellValue value) {
        return Double.isNaN(result) ? value.asNumber() : Math.max(result, value.asNumber());
    }
    
    @Override
    public String toString() {
        return "MAX(" + childToString() + ")";
    }
}
