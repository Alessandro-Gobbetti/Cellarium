/**
 * The "memory" to use during program execution.
 * In our little language, the memory consists
 * of a table of variables and 
 * an operand stack to hold intermediate values.
 */
public class Storage {
    
    private final OperandStack stack;
    private final VariableTable variables;
    
    /**
     * Create a Storage.
     * @param stack The OperandStack
     * @param variables The VariableTable
     */
    public Storage(final OperandStack stack, final VariableTable variables) {
        this.stack = stack;
        this.variables = variables;
    }
    
    /**
     * Get the OperandStack.
     * @return The OperandStack
     */
    public OperandStack getOperandStack() {
        return stack;
    }
    
    /**
     * Get the VariableTable.
     * @return The VariableTable
     */
    public VariableTable getVariableTable() {
        return variables;
    }
    
}
