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
    public CellValue eval(final Spreadsheet spreadsheet) {
        int count = 0;
               
        final int beginRow = getChild().getMinRow();
        final int beginCol = getChild().getMinCol();
        final int endRow = getChild().getMaxRow();
        final int endCol = getChild().getMaxCol();
        
        for (int row = beginRow; row <= endRow; row++) {
            for (int col = beginCol; col <= endCol; col++) {
                final Cell cell = spreadsheet.get(row,col);
                if (!(cell == null || cell.eval() instanceof EmptyCellValue  )) {
                    count++;
                }
            }
        }
        return new NumberCellValue(count);
    }
}
