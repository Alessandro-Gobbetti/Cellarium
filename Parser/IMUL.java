/**
 * IMUL multiples the top two values from the OperandStack
 * and pushes the result back to the OperandStack.
 */
public class IMUL extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(stack.pop() * stack.pop());
    }
    
    @Override
    public String toString() {
        return "IMUL";
    }
    
}
