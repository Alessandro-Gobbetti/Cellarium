package spreadsheet;


/**
 * Write a description of class AverageNodeCreator here.
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
