package spreadsheet;

import java.util.ArrayList;

/**
 * A Literal is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 */
public class Literal extends Node {
    
    private final double value;
    
    
    /**
     * Create a new IntLiteral node.
     * @param value the integer value this node evaluates to
     */
    public Literal(final double value) {
        super();
        this.value = value;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public String toString() {
        return "" + value;
    }
    
    @Override
    public CellValue eval() {
        return new NumberCellValue(value);
    }
    
    @Override
    public void addDependencies(final ArrayList<Cell> list) {
        //FIXME
    }
}
