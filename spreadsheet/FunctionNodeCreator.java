package spreadsheet;

import java.util.ArrayList;

/**
 * Write a description of class FunctionParser here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public abstract interface FunctionNodeCreator {
    
    public abstract Node create(ArrayList<Node> list);
}
