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

    public void compile(final Program p) {
        super.compile(p);
        p.append(new COS());
    }


    public Instruction getInstruction() {
        return new COS();
    }
    
    @Override
    public String toString() {
        return "cos(" + childToString() + ")";
    }
    
    @Override
    public double evalUnary(Node child) {
        return Math.cos(child.eval());
    }
}