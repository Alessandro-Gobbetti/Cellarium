package parser;

/**
 * A Variable is an AST node that 
 * corresponds to a literal integer value
 * (a number in the source code).
 */
public class Variable implements Node  {
    
    private final String name;

    /**
     * Create a new IntVariable node.
     * @param name the string to name the variable
     */
    public Variable(final String name) {
        super();
        this.name = name;
    }
    
    @Override
    public Type getType() {
        return Type.INVALID;
    }

    @Override
    public boolean isConstant() {
        return false;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public double eval() {
        return Double.NaN;
    }
}
