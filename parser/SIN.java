package parser;

import java.lang.Math;
/**
 * SIN negates the top value from the OperandStack
 * and pushes the result back to the OperandStack.
 */
public class SIN extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(Math.sin(stack.pop()));
    }
        
    @Override
    public String toString() {
        return "SIN";
    }

}
