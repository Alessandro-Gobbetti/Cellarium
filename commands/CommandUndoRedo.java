package commands;


/**
 * Abstract class for undo and redo commands.
 *
 * @author Alessadro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public abstract class CommandUndoRedo extends NotUndoableNotStateChangingCommand {
    
  private boolean isUndoFlag;
  
  /**
   * Creator for CommandUndoRedo.
   * @param isUndo an undo flag to set the command to be undo or redo.
   */
  public CommandUndoRedo(final boolean isUndo) {
      isUndoFlag = isUndo;
  }
  
  @Override
  public boolean isUndo() {
      return isUndoFlag;
  }
  
  @Override
  public boolean isRedo() {
      return !isUndo();
  }
  
  @Override
  public String getName() {
      return isUndo() ? "UNDO" : "REDO";
  }
  
  @Override
  public void doit() {
      setLastOperationOk();
  }
}
