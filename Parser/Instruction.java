/**
 * An IJVM-like instruction.
 */
public class Instruction {
    
    /**
     * Execute this instruction.
     * @param storage The "memory" to use during the execution
     */
    public void execute(final Storage storage) {
        // implemented in subclasses
    }
    
    /**
     * Get a String with the disassembled instruction.
     * @return A String-representation of this instruction.
     */
    public String toString() {
        return "?";
    }
    
}
