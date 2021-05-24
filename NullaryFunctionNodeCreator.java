import java.util.ArrayList;

/**
 * Write a description of class UnaryNodeCreator here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public abstract class NullaryFunctionNodeCreator implements FunctionNodeCreator {
    
    @Override
    public Node create(ArrayList<Node> list) {
        if (list.size() != 0) {
            return new Error("Err:Syntax" , "Expected no parameter, got " + list.size() + " parameters.");
        } else { 
            return nullaryCreate();
        }
    }
    
    public abstract Node nullaryCreate();
}
