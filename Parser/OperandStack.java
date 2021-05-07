/**
 * The OperandStack is a stack holding
 * the temporary values during execution.
 * (If you took Computer Architecture at USI,
 * you should remember this).
 */
public class OperandStack {
    

    private final int[] intStack;
    private int sp;
    
    /**
     * Create an empty OperandStack,
     * with space for at most 10 elements.
     */
    public OperandStack() {
        intStack = new int[10];
        sp = -1;
    }
    
    /**
     * Push the given value on the stack.
     * @param value The value to push
     */
    public void push(final int value) {
        ++sp;
        intStack[sp] = value;
    }

    
    /**
     * Pop the top-most value off the stack.
     * @return the top-most value
     */
    public int pop() {
        final int result = intStack[sp];
        sp--;
        return result;
    }
}
