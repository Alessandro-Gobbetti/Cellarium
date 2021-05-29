package spreadsheet;

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
        return "(" + leftChild.toString() + getOp() + rightChild.toString() + ")";
    }
    
    /**
     * To compute the result of a binary operation.
     * 
     * @param left  the left operand
     * @param right  the right operand
     * @return the result of a binary operation
     */
    public CellValue computeBinary(final CellValue left, final CellValue right) {
        if (!left.isConvertibleToNumber() || !right.isConvertibleToNumber()) {
            return new ErrorCellValue("#VALUE!", "Expected a number");
        } else {
            return computeBinaryFromNumbers(left.asNumber(), right.asNumber());
        }
    }
    
    /**
     * Compute the value of a cell given two numbers.
     * 
     * @param leftValue the first opernad to compute the result with.
     * @param rightValue the second operand to compute the result with.
     * @return the new CellValue of the cell.
     */
    public abstract CellValue computeBinaryFromNumbers(final double leftValue, 
                                                       final double rightValue);
    
    @Override
    public CellValue eval() {
        return computeBinary(leftChild.eval(), rightChild.eval());
    }
    
    @Override
    public void addDependencies(final ArrayList<Cell> list) {
        leftChild.addDependencies(list);
        rightChild.addDependencies(list);
    }
}
