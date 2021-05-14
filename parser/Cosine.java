package parser;


/**
 * An integer negation (e.g., -5, or -x).
 */
public class Cosine extends UnaryOperation {
    
    
    /**
     * Create a new Cos node.
     * @param child the operand we will negate
     */
    public Cosine(final Node child) {
        super(child);
    }
    
    @Override
    public String toString() {
        return "cos(" + childToString() + ")";
    }
    
    @Override
    public double computeUnary(final double child) {
        return Math.cos(child);
    }
}