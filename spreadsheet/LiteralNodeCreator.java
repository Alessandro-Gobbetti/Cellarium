package spreadsheet;

/**
 * Write a description of class SinNodeCreator here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class LiteralNodeCreator extends NullaryFunctionNodeCreator {
    
    private final double value;
    
    /**
     * Constructor for LiteralNodeCreator.
     * @param value the value with which to create a Literal node.
     */
    public LiteralNodeCreator(final double value) {
        this.value = value;
    }
    
    @Override
    public Node nullaryCreate() {
        return new Literal(value);
    }
}
