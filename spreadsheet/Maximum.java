package spreadsheet;

/**
 * A Maximum is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 */
public class Maximum extends RangeOperation {
    
    /**
     * Create a new Maximum node.
     * @param child the range over which to calculate the average.
     */
    public Maximum(final CellReferenceRange child) {
        super(child);
    }
    
    @Override
    public String toString() {
        return "MAX(" + childToString() + ")";
    }
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        double max = 0;
        boolean input = false;
               
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
                    if (!input) {
                        input = true;
                        max = value.asNumber();
                    } else {
                        max = Math.max(value.asNumber(), max);
                    }
                }
            }
        }
        return new NumberCellValue(max);
    }
}
