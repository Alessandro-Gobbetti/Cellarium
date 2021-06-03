package spreadsheet;

/**
 * A Average is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 * It shows the average of a certain Range.
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class Average extends RangeOperation {
    
    
    /**
     * Create a new Average node.
     * @param child the range over which to calculate the average.
     */
    public Average(final CellReferenceRange child) {
        super(child);
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
        return result;
    }
    
    @Override
    protected double computeResult(final double result) {
        final RangeOperation count = new Count(getChild());
        final RangeOperation sum = new Sum(getChild());
        return sum.eval().asNumber() / count.eval().asNumber();
    }
}
