import java.util.ArrayList;

/**
 * Write a description of class CosineNodeCreator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CosineNodeCreator extends UnaryFunctionNodeCreator {
    
    public Node unaryCreate(Node parameter) {
        return new Cosine(parameter);
    }
}
