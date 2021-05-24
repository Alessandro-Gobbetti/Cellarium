/**
 * Write a description of class SinNodeCreator here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class LiteralNodeCreator extends NullaryFunctionNodeCreator {
    
    private final double value;
    
    public LiteralNodeCreator(final double value) {
        this.value = value;
    }
    
    
    public Node nullaryCreate() {
        return new Literal(value);
    }
}
