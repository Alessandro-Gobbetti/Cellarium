package spreadsheet;


/**
 * This class creates a Sum node.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class SumNodeCreator extends UnaryRangeFunctionNodeCreator {
    
    @Override
    public Node unaryRangeCreate(final CellReferenceRange range) {
        return new Sum(range);
    }
}
