import java.util.ArrayList;

/**
 * Write a description of class UnaryNodeCreator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class UnaryFunctionNodeCreator implements FunctionNodeCreator {
    
    @Override
    public Node create(ArrayList<Node> list) {
        if (list.size() != 1) {
            return new Error("Err:Syntax" , "Expected one parameter, got " + list.size() + " parameters.");
        } else { 
            return unaryCreate(list.get(0));
        }
    }
    
    public abstract Node unaryCreate(Node parameter);
}
