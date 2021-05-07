package parser;

/**
 * LOAD push the value corresponding to the given variable name onto the operand stack.
 */
public class LOAD extends Instruction {
    
    private final String name;
    
    /**
     * Constractor for DLOAD.
     * @param name the name of the variable to load.
     */
    public LOAD(final String name) {
        super();
        this.name = name;
    }
    
    @Override
    public void execute(final Storage storage) {
        final double variable = storage.getVariableTable().get(name).doubleValue();
        storage.getOperandStack().push(variable);
    }
    
    @Override
    public String toString() {
        return "LOAD " + name;
    }
    
}
