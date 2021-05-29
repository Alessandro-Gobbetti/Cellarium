package commands;


/**
 * Write a description of class Command here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Command {
    
    private boolean wasLastOperationSuccessful;
    private boolean wasLastOperationAborted;
    private String lastOperationMessage;
    
    /**
     * Creator for command.
     */
    public Command() {
        wasLastOperationSuccessful = true;
        wasLastOperationAborted = false;
        lastOperationMessage = "init";
    }
    
    /**
     * To get the name of the command.
     * @return the name of the command.
     */
    public abstract String getName();
    
    /**
     * True if the command change the state, false otherwise (E.g. print, save...).
     * @return true if the command change the state.
     */
    public abstract boolean isChangingState();
    
    /**
     * True if the command is not capable of undo/redo.
     * @return true if the command is not capable of undo/redo.
     */
    public abstract boolean isUndoable();
    
    /**
     * To execute the command.
     */
    public abstract void doit();
    
    /**
     * To undo the command.
     */
    public abstract void undo();
    
    /**
     * To redo the command.
     */
    public abstract void redo();
    
    /**
     * Return True if the command is an undo command. By default is false.
     * @return True if the command is an undo command. By default is false.
     */
    public boolean isUndo() { 
        return false;
    }
    
    /**
     * Return True if the command is an redo command. By default is false.
     * @return True if the command is an redo command. By default is false.
     */
    public boolean isRedo() { 
        return false; 
    }
    
    /**
     * True if the operation executed correctly.
     * If the operation was not successful the state must not be change.
     * @return True if the operation executed correctly.
     */
    public boolean getLastOperationSuccessful() {
        return wasLastOperationSuccessful;
    }
    
    /**
     * True if the last operation aborted without changing state.
     * @return True if the last operation aborted without changing state.
     */
    public boolean getLastOperationAborted() {
        return wasLastOperationAborted;
    }
    
    /**
     * A message for the last operation.
     * @return the last operation message.
     */
    public String getLastOperationMessage() {
        return lastOperationMessage;
    }
    
    /**
     * To set the last operation status.
     */
    protected void setLastOperationStatus(final boolean success,
                                          final boolean aborted,
                                          final String message) {
        wasLastOperationSuccessful = success;
        wasLastOperationAborted = aborted;
        lastOperationMessage = message;
    }
    
    /**
     * To set the last operation ok.
     */
    protected void setLastOperationOk() {
        setLastOperationStatus(true, false, "Ok");
    }
    
}
