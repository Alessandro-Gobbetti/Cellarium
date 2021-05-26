package spreadsheet;

/**
 * A Sum is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 */
public class Sum extends RangeOperation {
    
    /**
     * Create a new Average node.
     * @param child the range over which to calculate the average.
     */
    public Sum(final CellReferenceRange child) {
        super(child);
    }
    
    @Override
    public String toString() {
        return "SUM(" + childToString() + ")";
    }
    
    @Override
    public double computeNext(final double result, final CellValue value) {
        if (Double.isNaN(result)) {
            return value.asNumber();
        } else {
            return result + value.asNumber();
        }
    }
    
}
