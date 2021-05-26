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
        if (value instanceof EmptyCellValue) {
            // do not increase the count
            return result;
        } else if (Double.isNaN(result)) {
            return 1.0;
        } else {
            return result + 1.0;
        }
    }

}
