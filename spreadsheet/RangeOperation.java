package spreadsheet;

import java.util.ArrayList;

/**
 * A Binary Operation.
 */
public abstract class RangeOperation extends Node {
    
    private final CellReferenceRange child;
    
    /**
     * Create a new RangeOperation node.
     * @param child the range this node evaluates to.
     */
    public RangeOperation(final CellReferenceRange child) {
        super();
        this.child = child;
    }

    /**
     * To get the child node.
     * @return the child node
     */
    protected CellReferenceRange getChild() {
        return child;
    }
    
    @Override
    public boolean isConstant() {
        return child.isConstant();
    }

    @Override
    public abstract String toString();
    
    /**
     * Returns the child as a String.
     * @return the child as a String 
     */
    public String childToString() {
        return child.toString();
    }
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        double result = Double.NaN;
        
        final int beginRow = child.getMinRow();
        final int beginCol = child.getMinCol();
        final int endRow = child.getMaxRow();
        final int endCol = child.getMaxCol();
        
        for (int row = beginRow; row <= endRow; row++) {
            for (int col = beginCol; col <= endCol; col++) {
                final CellValue value = spreadsheet.getOrCreate(row,col).eval();
                if (useOnlyNumbers() && !value.isConvertibleToNumber()) {
                    return new ErrorCellValue("#VALUE", "Expected a number");
                }
                result = computeNext(result, value);
            }
        }
        return new NumberCellValue(computeResult(result));
    }
    
    /**
     * Return True if this node works with numbers.
     * 
     * @return true if this node works with numbers.
     */
    protected abstract boolean useOnlyNumbers();
    
    /**
     * helper method to compute next result during evaluation.
     * 
     * @return the next result.
     */
    protected abstract double computeNext(final double result, final CellValue value);
    
    /**
     * helper method to compute the result of the evaluation.
     * 
     * @return the result of the evaluation.
     */
    protected double computeResult(final double result) {
        // implemented in subclasses if different
        return result;
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        child.addDependencies(spreadsheet, list);
    }
}