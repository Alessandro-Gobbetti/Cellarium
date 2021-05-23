import java.util.ArrayList;

/**
 * Write a description of class FunctionParser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract interface FunctionNodeCreator {
    public Node create(ArrayList<Node> list);
}
