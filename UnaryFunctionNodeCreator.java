import java.util.ArrayList;

/**
 * Write a description of class UnaryNodeCreator here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public abstract class UnaryFunctionNodeCreator implements FunctionNodeCreator {
    
    @Override
    public Node create(final ArrayList<Node> list) {
        if (list.size() != 1) {
            return new Error("Err:Syntax" , "Expected one parameter, got " + list.size() + " parameters.");
        } else { 
            return unaryCreate(list.get(0));
        }
    }
    
    public abstract Node unaryCreate(Node parameter);
}
