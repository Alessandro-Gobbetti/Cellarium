package spreadsheet;

/**
 * This Class creates a Logarithm node.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class LogarithmNodeCreator extends UnaryFunctionNodeCreator {
    
    @Override
    public Node unaryCreate(final Node parameter) {
        return new Logarithm(parameter);
    }
}
