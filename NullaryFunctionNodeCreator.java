import java.util.ArrayList;

/**
 * Write a description of class NullarayFunctionCreator here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public abstract class NullaryFunctionNodeCreator implements FunctionNodeCreator {
    
    @Override
    public Node create(ArrayList<Node> list) {
        if (list.size() != 0) {
            return new Error(
                            "Err:Syntax" , "Expected no parameter, got " + list.size() + " parameters."
                            );
        } else { 
            return nullaryCreate();
        }
    }
    
    /**
     * Creates a new nullary function Node.
     */
    public abstract Node nullaryCreate();
}
