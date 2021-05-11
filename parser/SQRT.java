package parser;

import java.lang.Math;
/**
 * SQRT computes the square root of the top value from the OperandStack
 * and pushes the result back to the OperandStack.
 */
public class SQRT extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(Math.sqrt(stack.pop()));
    }
        
    @Override
    public String toString() {
        return "SQRT";
    }

}
