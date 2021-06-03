package spreadsheet;


/**
 * This class creates a Minimum node.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class MinimumNodeCreator extends UnaryRangeFunctionNodeCreator {
    
    @Override
    public Node unaryRangeCreate(final CellReferenceRange range) {
        return new Minimum(range);
    }
}
