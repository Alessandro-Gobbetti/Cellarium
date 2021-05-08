package parser;

/**
 * The OperandStack is a stack holding
 * the temporary values during execution.
 * (If you took Computer Architecture at USI,
 * you should remember this).
 */
public class OperandStack {
    

    private final double[] stack;
    private int sp;
    
    /**
     * Create an empty OperandStack,
     * with space for at most 10 elements.
     */
    public OperandStack() {
        stack = new double[10];
        sp = -1;
    }
    
    /**
     * Push the given value on the stack.
     * @param value The value to push
     */
    public void push(final double value) {
        ++sp;
        stack[sp] = value;
    }

    
    /**
     * Pop the top-most value off the stack.
     * @return the top-most value
     */
    public double pop() {
        final double result = stack[sp];
        sp--;
        return result;
    }
}
