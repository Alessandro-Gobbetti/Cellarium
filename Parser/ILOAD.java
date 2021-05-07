/**
 * DLOAD push the value corresponding to the given variable name onto the operand stack.
 */
public class ILOAD extends Instruction {
    
    private final String name;
    
    /**
     * Constractor for ILOAD.
     * @param name the name of the variable to load.
     */
    public ILOAD(final String name) {
        super();
        this.name = name;
    }
    
    @Override
    public void execute(final Storage storage) {
        final int variable = storage.getVariableTable().get(name);
        storage.getOperandStack().push(variable);
    }
    
    @Override
    public String toString() {
        return "ILOAD " + name;
    }
    
}
