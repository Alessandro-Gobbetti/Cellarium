package spreadsheet;

/**
 * A Average is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 */
public class Average extends RangeOperation {
    
    private double currentCount;
    
    /**
     * Create a new Average node.
     * @param child the range over which to calculate the average.
     */
    public Average(final CellReferenceRange child) {
        super(child);
        currentCount = 0;
    }
    
    @Override
    protected boolean useOnlyNumbers() { 
        return true;
    }
    
    @Override
    public String toString() {
        return "AVERAGE(" + childToString() + ")";
    }
    
    @Override
    protected double computeNext(final double result, final CellValue value) {
        currentCount++;
        if (Double.isNaN(result)) {
            return value.asNumber();
        } else {
            return result + value.asNumber();
        }
    }
    
    @Override
    protected double computeResult(final double result) {
        return result / currentCount;
    }
}
