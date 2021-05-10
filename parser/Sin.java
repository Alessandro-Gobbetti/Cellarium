package parser;

/**
 * A sin operation.
 */
public class Sin extends UnaryOperation {
    
    /**
     * Create a new Sin node.
     * @param child the operand we will compute the sin
     */
    public Sin(final Node child) {
        super(child);
    }
    
    @Override
    public Instruction getInstruction() {
        return new SIN();
    }

    @Override
    public String toString() {
        return "(sin(" + super.getChild().toString() + "))";
    }
    
    public double eval() {
        return 0;
    }
}
