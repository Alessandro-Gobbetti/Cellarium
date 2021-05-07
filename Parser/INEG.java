/**
 * INEG negates the top value from the OperandStack
 * and pushes the result back to the OperandStack.
 */
public class INEG extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(- stack.pop());
    }
        
    @Override
    public String toString() {
        return "INEG";
    }

}
