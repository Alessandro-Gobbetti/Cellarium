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
    public void compile(final Program p) {
        super.compile(p);
        p.append(new SQRT());
    }

    @Override
    public String toString() {
        return "(sqrt(" + super.getChild().toString() + "))";
    }
    
    public double eval() {
        return 0;
    }
}
