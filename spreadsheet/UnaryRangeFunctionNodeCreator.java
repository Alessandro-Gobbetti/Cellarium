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
            CellReference reference = (CellReference)parameter;
            CellReferenceRange range = new CellReferenceRange(reference, reference);
            return unaryRangeCreate(range);
        } else {
            return new Error("Err:Syntax", 
                             "Expected a range as parameter, got " + parameter.toString());
        } 
    }
    
    public abstract Node unaryRangeCreate(CellReferenceRange parameter); 
}
