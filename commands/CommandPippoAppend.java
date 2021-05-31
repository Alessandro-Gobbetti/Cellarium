package commands;


/**
 * The only goal for this class is to verify 
 * the functionalities of commands and test undo and redo.
 * This class appends the string "pippo" to the first element in a string array.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class CommandPippoAppend extends UndoableStateChangingCommand {
    
    private String[] currentString;
    
    /**
     * Constructor for CommandPippoAppend.
     * @param currentString the string array.
     */
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
