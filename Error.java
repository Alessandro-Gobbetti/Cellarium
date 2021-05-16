import java.util.ArrayList;

/**
 * A Error is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 */
public class Error extends Node {
    
    // short code to display in the cell
    private final String code;
    // to explain the error
    private final String text;
    
    
    /**
     * Create a new IntLiteral node.
     * @param value the integer value this node evaluates to
     */
    public Error(final String code, final String text) {
        super();
        this.code = code;
        this.text = text;
    }

    @Override
    public boolean isConstant() {
        return true;
    }
    
    @Override
    public boolean isError() {
        return true;
    }

    @Override
    public String toString() {
        return text;
    }
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        return new ErrorCellValue(code, text);
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        //FIXME
    }
}
