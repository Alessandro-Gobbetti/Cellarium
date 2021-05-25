package spreadsheet;

import java.util.ArrayList;

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
    public CellValue eval(final Spreadsheet spreadsheet) {
        double sum = 0;
               
        final int beginRow = getChild().getMinRow();
        final int beginCol = getChild().getMinCol();
        final int endRow = getChild().getMaxRow();
        final int endCol = getChild().getMaxCol();
        
        for (int row = beginRow; row <= endRow; row++) {
            for (int col = beginCol; col <= endCol; col++) {
                final CellValue value = spreadsheet.getOrCreate(row,col).eval();
                if (!value.isConvertibleToNumber()) {
                    return new ErrorCellValue("#VALUE", "Expected a number");
                }
                sum = sum + value.asNumber();
            }
        }
        return new NumberCellValue(sum);
    }
}
