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
            return new Error("Err:Syntax" , 
                            "Expected one parameter, got " + list.size() + " parameters.");
        } else { 
            return unaryCreate(list.get(0));
        }
    }
    
    /**
     * To create a unary node.
     * @param parameter the parameter to pass to the new node
     * @return a new node
     */
    public abstract Node unaryCreate(Node parameter);
}
