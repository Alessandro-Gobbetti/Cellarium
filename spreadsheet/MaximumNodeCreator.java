package spreadsheet;


/**
 * This Clas creates a Maximum node.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class MaximumNodeCreator extends UnaryRangeFunctionNodeCreator {
    
    @Override
    public Node unaryRangeCreate(final CellReferenceRange range) {
        return new Maximum(range);
    }
}
