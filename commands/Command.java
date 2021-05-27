package commands;


/**
 * Write a description of class Command here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract interface Command {
    

    public abstract void execute();
    
    public abstract void unexecute();
    public abstract void reexecute();
    
    public abstract boolean isUnexecutable();
    //...
}
