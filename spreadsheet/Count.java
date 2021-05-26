package spreadsheet;


/**
 * A Count is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 */
public class Count extends RangeOperation {
    
    /**
     * Create a new Count node.
     * @param child the range over which to calculate the average.
     */
    public Count(final CellReferenceRange child) {
        super(child);
    }
    
    @Override
    public String toString() {
        return "COUNT(" + childToString() + ")";
    }
    
    @Override
    public double computeNext(final double result, final CellValue value) {
        if (Double.isNaN(result)) {
            // do not increase the count
            return (value instanceof EmptyCellValue) ? 0.0 : 1.0;
        } else if (value instanceof EmptyCellValue) {
            return result;
        } else {
            return result + 1.0;
        }
    }

}
