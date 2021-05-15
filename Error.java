/**
 * A Error is an AST node that 
 * corresponds to a literal value
 * (a number in the source code).
 */
public class Error implements Node {
    
    private final String text;
    
    
    /**
     * Create a new IntLiteral node.
     * @param value the integer value this node evaluates to
     */
    public Error(final String text) {
        super();
        this.text = text;
    }

    @Override
    public boolean isConstant() {
        return true;
    }
    
    @Override
    public Type getType() {
        return Type.STRING;
    }
    
    @Override
    public boolean isError() {
        return true;
    }

    @Override
    public String toString() {
        return text;
    }
    
    @Override
    public CellValue eval() {
        return new ErrorCellValue(text);
    }
}
