package commands;


/**
 * Write a description of class UndoableCommand here.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public abstract class UndoableStateChangingCommand extends Command {
    
    @Override
    public boolean isChangingState() {
        return true;
    }
    
    @Override
    public boolean isUndoable() {
        return true;
    }

}
