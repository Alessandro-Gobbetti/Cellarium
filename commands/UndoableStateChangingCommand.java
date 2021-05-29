package commands;


/**
 * Write a description of class UndoableCommand here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
