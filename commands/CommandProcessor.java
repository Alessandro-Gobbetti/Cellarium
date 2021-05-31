package commands;

import java.util.ArrayList;

/**
 * Write a description of class CommandProcessor here.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class CommandProcessor {
    
    private ArrayList<Command> past;
    private ArrayList<Command> future;
    private boolean wasLastOperationSuccessful;
    private boolean wasLastOperationAborted;
    private String lastOperationMessage;
    
    /**
     * Creator for CommandProcessor.
     */
    public CommandProcessor() {
        past = new ArrayList<Command>();
        future = new ArrayList<Command>();
        wasLastOperationSuccessful = true;
        wasLastOperationAborted = false;
        lastOperationMessage = "init";
    }
    
    /**
     * To execute a given command.
     * @param command the command to execute.
     */
    public void doCommand(final Command command) {
        if (command.isUndo()) {
            undoLastCommand();
        } else if (command.isRedo()) {
            redoLastCommand();
        } else {
            command.doit();
            wasLastOperationSuccessful = command.wasLastOperationSuccessful();
            wasLastOperationAborted = command.wasLastOperationAborted();
            lastOperationMessage = command.getLastOperationMessage();
            if (!wasLastOperationSuccessful) {
                if (!wasLastOperationAborted && command.isChangingState()) {
                    // we cannot trust the history.
                    past.clear();
                    future.clear();
                }
            } else if (command.isChangingState()) {
                future.clear();
                if (command.isUndoable()) {
                    past.add(command);
                } else {
                    past.clear();
                }
            } // when the command do not change the state we do not add it to the history.
        }
    }
    
    /**
     * To undo the last command.
     */
    public void undoLastCommand() {
        if (past.isEmpty()) { 
            wasLastOperationSuccessful = false;
            lastOperationMessage = "No commands to undo!";
        } else {
            final Command command = past.get(past.size() - 1);
            past.remove(past.size() - 1);
            command.undo();
            wasLastOperationSuccessful = command.wasLastOperationSuccessful();
            wasLastOperationAborted = command.wasLastOperationAborted();
            lastOperationMessage = command.getLastOperationMessage();
            if (!wasLastOperationSuccessful) {
                if (command.isChangingState()) {
                    // if the undo is aborted we cannot trust the history.
                    // this should never happen.
                    past.clear();
                    future.clear();
                }
            } else {
                future.add(command);
            }
        }
    }
    
    /**
     * To redo the last command.
     */
    public void redoLastCommand() {
        if (future.isEmpty()) { 
            wasLastOperationSuccessful = false;
            lastOperationMessage = "No commands to redo!";
        } else {
            final Command command = future.get(future.size() - 1);
            future.remove(future.size() - 1);
            command.redo();
            wasLastOperationSuccessful = command.wasLastOperationSuccessful();
            wasLastOperationAborted = command.wasLastOperationAborted();
            lastOperationMessage = command.getLastOperationMessage();
            if (!wasLastOperationSuccessful) {
                if (command.isChangingState()) {
                    // if the redo is aborted we cannot trust the history.
                    // this should never happen.
                    past.clear();
                    future.clear();
                }
            } else {
                past.add(command);
            }
        }
    }
    
    /**
     * True if the last operation executed correctly.
     * If the last operation was not successful the state must not be change.
     * @return True if the last operation executed correctly.
     */
    public boolean wasLastOperationSuccessful() {
        return wasLastOperationSuccessful;
    }
    
    /**
     * True if the last operation aborted without changing state.
     * @return True if the last operation aborted without changing state.
     */
    public boolean wasLastOperationAborted() {
        return wasLastOperationAborted;
    }
    
    /**
     * A message from the last operation.
     * @return the last operation message.
     */
    public String getLastOperationMessage() {
        return lastOperationMessage;
    }
    
    /**
     * To get the amount of undoable commands.
     * @return the amount of undoable commands
     */
    public int getUndoCount() {
        return past.size();
    }
    
    /**
     * To get the amount of redoable commands.
     * @return the amount of redoable commands
     */
    public int getRedoCount() {
        return future.size();
    }
    
    /**
     * Returns the name of the command at a given position in the undo list.
     * @param index the index of the command.
     * @return the name of the command at a given position in the undo list
     */
    public String getUndoCommandName(final int index) {
        return past.get(past.size() - 1 - index).getName();
    }
    
    /**
     * Returns the name of the command at a given position in the redo list.
     * @param index the index of the command.
     * @return the name of the command at a given position in the redo list
     */
    public String getRedoCommandName(final int index) {
        return future.get(future.size() - 1 - index).getName();
    }
}
