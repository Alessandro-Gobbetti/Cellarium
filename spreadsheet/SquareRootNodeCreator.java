package spreadsheet;

/**
 * Write a description of class SquareRootNodeCreator here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class SquareRootNodeCreator extends UnaryFunctionNodeCreator {
    
    @Override
    public Node unaryCreate(final Node parameter) {
        return new SquareRoot(parameter);
    }
}
