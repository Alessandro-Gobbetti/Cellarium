/**
 * Write a description of class CosineNodeCreator here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class CosineNodeCreator extends UnaryFunctionNodeCreator {
    
    public Node unaryCreate(final Node parameter) {
        return new Cosine(parameter);
    }
}
