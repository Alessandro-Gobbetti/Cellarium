package spreadsheet;

/**
 * Write a description of class RangeFunctionNodeCreator here.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public abstract class UnaryRangeFunctionNodeCreator extends UnaryFunctionNodeCreator {
    // check if instance node of CellReference or Range...
    @Override
    public Node unaryCreate(final Node parameter) {
        if (parameter instanceof CellReferenceRange) {
            return unaryRangeCreate((CellReferenceRange)parameter);
        } else if (parameter instanceof CellReference) {
            final CellReference reference = (CellReference)parameter;
            final CellReferenceRange range = new CellReferenceRange(reference, reference);
            return unaryRangeCreate(range);
        } else {
            return new Error("Err:Syntax", 
                             "Expected a range as parameter, got " + parameter.toString());
        } 
    }
    
    /**
     * Creates a unary range node.
     * 
     * @param parameter the range to create the node with.
     * @return a new range node.
     */
    public abstract Node unaryRangeCreate(final CellReferenceRange parameter); 
}
