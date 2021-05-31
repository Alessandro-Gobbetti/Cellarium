package spreadsheet;

/**
 * This Clas creates a Cosine node.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class CosineNodeCreator extends UnaryFunctionNodeCreator {
    
    @Override
    public Node unaryCreate(final Node parameter) {
        return new Cosine(parameter);
    }
}
