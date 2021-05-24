package spreadsheet;

import java.util.ArrayList;

/**
 * A Unary Operation.
 */
public abstract class UnaryOperation extends Node {
    
    private final Node child;
    
    
    /**
     * Create a new UnaryOperation node.
     * @param child the operand
     */
    public UnaryOperation(final Node child) {
        super();
        this.child = child;
    }
    
    /**
     * To get the child node.
     * @return the child node
     */
    protected Node getChild() {
        return child;
    }
    
    @Override
    public boolean isConstant() {
        return child.isConstant();
    }

    @Override
    public String toString() {
        return "=" + child.toString();
    }
    
    /**
     * Returns the child as a String.
     * @return the child as a String 
     */
    public String childToString() {
        return child.toString();
    }
    
    /**
     * To compute the result of a unary operation.
     * @param child  the operand
     * @return the result of a unary operation
     */
    public CellValue computeUnary(final CellValue child) {
        if (!child.isConvertibleToNumber()) {
            return new ErrorCellValue("#VALUE", "Expected a number");
        } else {
            return computeUnaryFromNumber(child.asNumber());
        }
    }
    
    public abstract CellValue computeUnaryFromNumber(final double child);
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        return computeUnary(child.eval(spreadsheet));
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        child.addDependencies(spreadsheet, list);
    }
}
