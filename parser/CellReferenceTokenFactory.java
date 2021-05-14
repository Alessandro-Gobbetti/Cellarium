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
        super("[$_][A-Z+][$_][0-9]+");
    }

    /**
     * Produce a token.
     * @return the currently found token
     */
    public Token getToken() {
        return new Token(TokenType.IDENTIFIER, getTokenText(), getTokenStartPosition());
    }
    
}
//---
