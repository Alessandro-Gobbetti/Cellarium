package spreadsheet;

/**
 * This Class creates a Sin node.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class SineNodeCreator extends UnaryFunctionNodeCreator {
    
    @Override
    public Node unaryCreate(final Node parameter) {
        return new Sine(parameter);
    }
}
