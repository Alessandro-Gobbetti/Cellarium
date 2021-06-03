package commands;


/**
 * Write a description of class UndoableCommand here.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public abstract class UndoableNotStateChangingCommand extends Command {
    
    @Override
    public boolean isChangingState() {
        return false;
    }
    
    @Override
    public boolean isUndoable() {
        return true;
    }
    
    // Since this command does not chage the state of the program,
    // undo and redo are no operation.
    @Override
    public void undo() {
        setLastOperationOk();
    }
    
    @Override
    public void redo() {
        setLastOperationOk();
    }
}
