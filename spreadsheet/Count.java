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
    protected boolean useOnlyNumbers() { 
        return false;
    }
    
    @Override
    public String toString() {
        return "COUNT(" + childToString() + ")";
    }
    
    @Override
    protected double computeNext(final double result, final CellValue value) {
        if (Double.isNaN(result)) {
            // do not increase the count
            return (value.type() == CellType.EMPTY) ? 0.0 : 1.0;
        } else {
            return (value.type() == CellType.EMPTY) ? result : result + 1.0;
        }
    }

}
