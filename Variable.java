import java.util.ArrayList;

/**
 * A Variable is an AST node that 
 * corresponds to a literal integer value
 * (a number in the source code).
 */
public class Variable extends Node  {
    
    private final String name;

    /**
     * Create a new IntVariable node.
     * @param name the string to name the variable
     */
    public Variable(final String name) {
        super();
        this.name = name;
    }

    @Override
    public boolean isConstant() {
        return false;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        return new EmptyCellValue();
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        //FIXME
    }
}
