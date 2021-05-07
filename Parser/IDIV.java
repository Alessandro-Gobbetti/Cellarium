/**
 * IDIV divides the top two values from the OperandStack
 * and pushes the result back to the OperandStack.
 */
public class IDIV extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final int operand1 = stack.pop();
        final int operand2 = stack.pop();
        stack.push(operand2 / operand1);
    }
    
    @Override
    public String toString() {
        return "IDIV";
    }
    
}
