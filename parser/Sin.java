package parser;

/**
 * An integer negation (e.g., -5, or -x).
 */
public class Sin extends UnaryOperation {
    
    
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public Sin(final Node child) {
        super(child);
    }
    
    @Override
    public void compile(final Program p) {
        super.compile(p);
        p.append(new SIN());
    }

    @Override
    public String toString() {
        return "(sin(" + super.getChild().toString() + "))";
    }
    
}
