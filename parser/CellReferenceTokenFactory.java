package parser;

//---
/**
 * A factory for tokens of type identifier.
 */
public class CellReferenceTokenFactory extends RegExTokenFactory {

    /**
     * Create an CellReferenceTokenFactory.
     */
    public CellReferenceTokenFactory() {
            super("[$]?[A-Z]+[$]?[0-9]+");
    }

    /**
     * Produce a token.
     * @return the currently found token
     */
    public Token getToken() {
        return new Token(TokenType.CELLREFERENCE, getTokenText(), getTokenStartPosition());
    }
    
}
//---
