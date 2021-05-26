package spreadsheet;

/**
 * A Minimum is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 */
public class Minimum extends RangeOperation {
    
    /**
     * Create a new Minimum node.
     * @param child the range over which to calculate the average.
     */
    public Minimum(final CellReferenceRange child) {
        super(child);
    }
    
    @Override
    public String toString() {
        return "MIN(" + childToString() + ")";
    }
    
    @Override
    public double computeNext(final double result, final double value) {
        return Double.isNaN(result) ? value : Math.min(result, value);
    }

}
