package spreadsheet;

/**
 * Write a description of class SinNodeCreator here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class SineNodeCreator extends UnaryFunctionNodeCreator {
    
    @Override
    public Node unaryCreate(final Node parameter) {
        return new Sine(parameter);
    }
}
