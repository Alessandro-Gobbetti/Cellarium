package spreadsheet;

import java.util.ArrayList;

/**
 * A Binary Operation.
 */
public abstract class RangeOperation extends Node {
    
    private final CellReferenceRange child;
    
    /**
     * Create a new IntLiteral node.
     * @param value the integer value this node evaluates to
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
    public abstract CellValue eval(final Spreadsheet spreadsheet);
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        child.addDependencies(spreadsheet, list);
    }
}
