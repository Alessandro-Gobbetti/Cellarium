package spreadsheet;

import java.util.ArrayList;

/**
 * A Error is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class Error extends Node {
    
    // short code to display in the cell
    private final String code;
    // to explain the error
    private final String text;
    
    
    /**
     * Create a new Error node.
     * @param code the code of the error
     * @param text a more detailed description of the error
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
    public CellValue eval() {
        return new ErrorCellValue(code, text);
    }
    
    @Override
    public void addDependencies(final ArrayList<Cell> list) {
        // no dependencies.
    }
}
