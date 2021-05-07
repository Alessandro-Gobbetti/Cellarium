/**
 * A special kind of StringTokenFactory,
 * which produces tokens that represent operators.
 */
public class OperatorTokenFactory extends StringTokenFactory {

    private final TokenType tokenType;

    
    /**
     * Create an OperatorTokenFactory for tokens representing the given operator.
     * @param operator the operator
     * @param tokenType the TokenType corresponding to this operator
     */
    public OperatorTokenFactory(final String operator, final TokenType tokenType) {
        super(operator);
        this.tokenType = tokenType;
    }

    @Override
    public Token getToken() {
        // return a token of this object's TokenType 
        // with its text and starting position
        return new Token(tokenType, getTokenText(), getTokenStartPosition());
    }

}
