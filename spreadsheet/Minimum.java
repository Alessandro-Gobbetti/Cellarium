package spreadsheet;

/**
 * A Minimum is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
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
    protected boolean useOnlyNumbers() { 
        return true;
    }
    
    @Override
    public String toString() {
        return "MIN(" + childToString() + ")";
    }
    
    @Override
    protected double computeNext(final double result, final CellValue value) {
        return Double.isNaN(result) ? value.asNumber() : Math.min(result, value.asNumber());
    }

}
