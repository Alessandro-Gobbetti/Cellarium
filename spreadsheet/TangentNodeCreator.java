package spreadsheet;

/**
 * This Class creates a Tangent node.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class TangentNodeCreator extends UnaryFunctionNodeCreator {
    
    @Override
    public Node unaryCreate(final Node parameter) {
        return new Tangent(parameter);
    }
}
