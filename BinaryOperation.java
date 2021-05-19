import java.util.ArrayList;

/**
 * A Binary Operation.
 */
public abstract class BinaryOperation extends Node {
    
    private final Node leftChild;
    private final Node rightChild;

    
    /**
     * Create a new BinaryOperations node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public BinaryOperation(final Node leftChild, final Node rightChild) {
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public boolean isConstant() {
        return leftChild.isConstant() && rightChild.isConstant();
    }
    
    /**
     * Get the string representation of the operator.
     * @return the string representation of the operator
     */
    public abstract String getOp();
    
    @Override
    public String toString() {
        return "=" + leftChild.toString() + getOp() + rightChild.toString();
    }
    
    /**
     * To compute the result of a binary operation.
     * @param left  the left operand
     * @param right  the right operand
     * @return the result of a binary operation
     */
    public abstract double computeBinary(final CellValue left, final CellValue right);
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        return new NumberCellValue(computeBinary(leftChild.eval(spreadsheet), rightChild.eval(spreadsheet)));
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        leftChild.addDependencies(spreadsheet, list);
        rightChild.addDependencies(spreadsheet, list);
    }
}
