package commands;


/**
 * Write a description of class CommandAppend here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CommandPippoAppend implements Command {
    
    private String currentString;
    
    public CommandPippoAppend(final String currentString) {
        this.currentString = currentString;
    }
    
    @Override
    public void execute() {
        currentString = currentString + "pippo";
    }
    
    @Override
    public void unexecute() {
        currentString = currentString.substring(0, currentString.length() - 5);
    }
    
    @Override
    public void reexecute() {
        execute();
    }
    
    @Override
    public boolean isUnexecutable() {
        return true;
    }
    
    public String getString() {
        return currentString;
    }
}
