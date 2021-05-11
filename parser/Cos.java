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
    public void compile(final Program p) {
        super.compile(p);
        p.append(new COS());
    }

    @Override
    public String toString() {
        return "(cos(" + super.getChild().toString() + "))";
    }
    
    public double eval() {
        return 0;
    }
}
