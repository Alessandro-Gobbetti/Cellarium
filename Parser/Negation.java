/**
 * An integer negation (e.g., -5, or -x).
 */
public class Negation extends Node {
    
    private final Node child;
    
    
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public Negation(final Node child) {
        super();
        this.child = child;
    }
    
    @Override
    public boolean isConstant() {
        return child.isConstant();
    }
    
    @Override
    public Type getType() {
        return Type.INT;
    }
    
    @Override
    public void compile(final Program p) {
        child.compile(p);
        p.append(new INEG());
    }

    @Override
    public String toString() {
        return "(-" + child.toString() + ")";
    }
    
}
