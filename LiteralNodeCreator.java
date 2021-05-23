import java.util.ArrayList;

/**
 * Write a description of class SinNodeCreator here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
