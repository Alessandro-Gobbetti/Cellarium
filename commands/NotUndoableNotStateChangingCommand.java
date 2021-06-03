package commands;


/**
 * Write a description of class NotUndoableCommand here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class NotUndoableNotStateChangingCommand extends Command {
    
    @Override
    public boolean isChangingState() {
        return false;
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
        // handle delete history
    }
    
    @Override
    public void redo() {
        assert false;
        setLastOperationStatus(false, false, "Cannot redo");
        // handle delete history
    }
}
