/**
 * A Variable is an AST node that 
 * corresponds to a literal integer value
 * (a number in the source code).
 */
public class Variable extends Node  {
    
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
    public void compile(final Program p) {
        p.append(new ILOAD(name));
    }
    
    @Override
    public String toString() {
        return name;
    }
}
