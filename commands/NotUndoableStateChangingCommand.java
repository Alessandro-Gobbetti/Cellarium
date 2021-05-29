package commands;


/**
 * Write a description of class NotUndoableCommand here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class NotUndoableStateChangingCommand extends Command {
    
    @Override
    public boolean isChangingState() {
        return true;
    }
    
    @Override
    public boolean isUndoable() {
        return false;
    }

    // Since this command is not undoable,
    // undo and redo are errors.
    @Override
    public void undo() {
        assert false;
        setLastOperationStatus(false, false, "Cannot undo");
        //FIXME handle delete history
    }
    
    @Override
    public void redo() {
        assert false;
        setLastOperationStatus(false, false, "Cannot redo");
        //FIXME handle delete history
    }
}
