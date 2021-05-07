package parser;

/**
 * BIPUSH pushes the given value onto the operand stack.
 */
public class BIPUSH extends Instruction {
    
    private final int value;
    
    /**
     * Constractor for BIPUSH.
     * @param value the value to push on the stack
     */
    public BIPUSH(final int value) {
        super();
        this.value = value;
    }
    
    @Override
    public void execute(final Storage storage) {
        storage.getOperandStack().push(value);
    }
    
    @Override
    public String toString() {
        return "BIPUSH " + value;
    }

}
