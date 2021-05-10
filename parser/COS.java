package parser;

import java.lang.Math;
/**
 * COS computes the sinus of the top value from the OperandStack
 * and pushes the result back to the OperandStack.
 */
public class COS extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(Math.cos(stack.pop()));
    }
        
    @Override
    public String toString() {
        return "COS";
    }

}
