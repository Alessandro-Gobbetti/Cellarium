package spreadsheet;

import java.util.ArrayList;

/**
 * Write a description of class FunctionParser here.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public abstract interface FunctionNodeCreator {
    
    /**
     * To create a node from a list of nodes.
     * This can also support funtions that requires an unknown number of parameters.
     * 
     * @param list the list of nodes.
     * @return a new node.
     */
    public abstract Node create(final ArrayList<Node> list);
}
