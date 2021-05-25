package spreadsheet;

import java.util.ArrayList;

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
    public CellValue eval(final Spreadsheet spreadsheet) {
        double min = 0;
               
        final int beginRow = getChild().getMinRow();
        final int beginCol = getChild().getMinCol();
        final int endRow = getChild().getMaxRow();
        final int endCol = getChild().getMaxCol();
        
        for (int row = beginRow; row <= endRow; row++) {
            for (int col = beginCol; col <= endCol; col++) {
                final CellValue value = spreadsheet.getOrCreate(row,col).eval();
                final Cell cell = spreadsheet.get(row,col);
                if (!value.isConvertibleToNumber()) {
                    return new ErrorCellValue("#VALUE", "Expected a number");
                } else if (!(cell == null || cell.eval() instanceof EmptyCellValue)) {
                    if (value.asNumber() < min) {
                        min = value.asNumber();
                    }
                }
            }
        }
        return new NumberCellValue(min);
    }
}
