package commands;

import java.util.Stack;

/**
 * Write a description of class CommandProcessor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CommandProcessor {
    
    private Stack<Command> past;
    private Stack<Command> future;
    
    public CommandProcessor() {
        past = new Stack<Command>();
        future = new Stack<Command>();
    }
    
    public void execute(final Command command) {
        future.clear();
        command.execute();
        if (command.isUnexecutable()) {
            past.push(command);
        }
    }
    
    public void unexecute() {
        if (!past.empty()) {
            final Command command = past.pop();
            command.unexecute();
            future.push(command);
        }
    }
    
    public void reexecute() {
        if (!future.empty()) {
            final Command command = future.pop();
            command.reexecute();
            past.push(command);
        }
    }
    
}
