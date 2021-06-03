package spreadsheet;

import java.util.ArrayList;

/**
 * The class NullarayFunctionCreator creates a NullaryNode.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public abstract class NullaryFunctionNodeCreator implements FunctionNodeCreator {
    
    @Override
    public Node create(final ArrayList<Node> list) {
        if (!list.isEmpty()) {
            return new Error("Err:Syntax", 
                             "Expected no parameter, got " + list.size() + " parameters.");
        } else { 
            return nullaryCreate();
        }
    }
    
    /**
     * Creates a new nullary function Node.
     * 
     * @return a new nullary node.
     */
    public abstract Node nullaryCreate();
}
