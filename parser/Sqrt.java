package parser;

/**
 * An sqare root computation, (ex. sqrt(9) = 3).
 */
public class Sqrt extends UnaryOperation {
    
    
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public Sqrt(final Node child) {
        super(child);
    }
    
    @Override
    public Instruction getInstruction() {
        return new SIN();
    }

    @Override
    public String toString() {
        return "(sqrt(" + super.getChild().toString() + "))";
    }
    
    public double compute(Node child) {
        return Math.sqrt(child.eval());
    }
}
