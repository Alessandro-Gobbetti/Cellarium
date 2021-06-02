package spreadsheet.lexer;

//---
/**
 * A factory for tokens of type identifier.
 */
public class FunctionTokenFactory extends RegExTokenFactory {

    /**
     * Create an FunctionTokenFactory.
     */
    public FunctionTokenFactory() {
        super("[a-zA-Z_][a-zA-Z_0-9]*[(]");
    }

    /**
     * Produce a token.
     * @return the currently found token
     */
    public Token getToken() {
        return new Token(TokenType.FUNCTION, getTokenText(), getTokenStartPosition());
    }
    
}
//---