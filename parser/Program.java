package parser;

import java.util.ArrayList;

/**
 * A compiled program,
 * consisting of a list of IJVM-like instructions.
 */
public class Program {
    
    private final ArrayList<Instruction> code;
    
    
    /**
     * Create a new empty program.
     */
    public Program() {
        this.code = new ArrayList<Instruction>();
    }

    /**
     * Append the given instruction to this program.
     * @param instruction The instruction to append
     */
    public void append(final Instruction instruction) {
        code.add(instruction);
    }
    
    /**
     * Get the number of instructions of this program.
     * @return the length, in number of instructions
     */
    public int getLength() {
        return code.size();
    }
    
    /**
     * Get the instruction at the given index in the program.
     * @param index the index
     * @return the instruction at the given index
     */
    public Instruction get(final int index) {
        return code.get(index);
    }
    
    /**
     * Execute this program, returning the result.
     * The result is the top element of the operand stack,
     * after executing all instructions.
     * @return The result of the execution.
     */
    public Number execute() {
        final VariableTable variables = new VariableTable();
        return execute(variables);
    }
    
    /**
     * Execute this program, returning the result.
     * The result is the top element of the operand stack,
     * after executing all instructions.
     * The program always return double, converting integer reslut if needed.
     * @param variables The variables with their values 
     *        (accessed by ILOAD instructions)
     * @return The result of the execution.
     */
    public Number execute(final VariableTable variables) {
        final OperandStack stack = new OperandStack();
        final Storage storage = new Storage(stack, variables);
        for (final Instruction instruction : code) {
            instruction.execute(storage);
        }
        return stack.pop();
    }
    
    
    /**
     * Disassemble this program into a String,
     * with one bytecode instruction per line.
     * @return A String-representation of this program.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (final Instruction instruction : code) {
            builder.append("  ");
            builder.append(instruction.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
    
}
