import java.util.ArrayList;

/**
 * Write a description of class SinNodeCreator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SineNodeCreator extends UnaryFunctionNodeCreator {
    
    public Node unaryCreate(Node parameter) {
        return new Sine(parameter);
    }
}
