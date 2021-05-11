package parser;


/**
 * An integer negation (e.g., -5, or -x).
 */
public class Cos extends UnaryOperation {
    
    
    /**
     * Create a new Cos node.
     * @param child the operand we will negate
     */
    public Cos(final Node child) {
        super(child);
    }
    
    @Override
    public Instruction getInstruction() {
        return new COS();
    }
    
    @Override
    public String toString() {
        return "(cos(" + super.getChild().toString() + "))";
    }
    
    public double compute(Node child) {
        return Math.cos(child.eval());
    }
}