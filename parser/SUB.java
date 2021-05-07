package parser;

/**
 * SUB subtracts the top value from the second-to-top value
 * of the OperandStack,
 * and pushes the result back to the OperandStack.
 */
public class SUB extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final double operand1 = stack.pop();
        final double operand2 = stack.pop();
        stack.push(operand2 - operand1);
    }
        
    @Override
    public String toString() {
        return "SUB";
    }

}
