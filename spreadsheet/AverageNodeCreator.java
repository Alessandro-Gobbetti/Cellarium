package spreadsheet;


/**
 * This class creates a Average Node.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class AverageNodeCreator extends UnaryRangeFunctionNodeCreator {
    
    @Override
    public Node unaryRangeCreate(final CellReferenceRange range) {
        return new Average(range);
    }
}
