package commands;


/**
 * Write a description of class CommandAppend here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CommandPippoAppend extends UndoableStateChangingCommand {
    
    private String[] currentString;
    
    public CommandPippoAppend(final String[] currentString) {
        super();
        this.currentString = currentString;
    }
    
    @Override
    public String getName() {
        return "CommandPippoAppend";
    }
    
    @Override
    public void doit() {
        currentString[0] = currentString[0] + "pippo";
    }
    
    @Override
    public void undo() {
        currentString[0] = currentString[0].substring(0, currentString[0].length() - 5);
    }
    
    @Override
    public void redo() {
        doit();
    }
    
}
