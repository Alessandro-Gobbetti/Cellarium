package parser;

/**
 * An integer negation (e.g., -5, or -x).
 */
public class Negation extends UnaryOperation {
        
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public Negation(final Node child) {
        super(child);
    }

    
    @Override
    public void compile(final Program p) {
        super.compile(p);
        p.append(new NEG());
    }

    @Override
    public String toString() {
        return "(-" + super.getChild().toString() + ")";
    }
    
}
