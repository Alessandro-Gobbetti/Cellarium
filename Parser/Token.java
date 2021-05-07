/**
 * A token is a contiguous sequence of characters in a text,
 * such as an identifier, an operator, or a literal.
 */
public final class Token {

    private final TokenType type;
    private final String text;
    private final int startPosition;

    
    /**
     * Create a new Token.
     * @param type the kind of token
     * @param text the contents (characters) making up the token
     * @param startPosition the position in the text where the token starts
     */
    public Token(final TokenType type, final String text, final int startPosition) {
        this.type = type;
        this.text = text;
        this.startPosition = startPosition;
    }

    /**
     * Get the type of this token.
     * @return the kind of token this is
     */
    public TokenType getType() {
        return type;
    }

    /**
     * Get the text making up this token.
     * @return the contents of this token
     */
    public String getText() {
        return text;
    }

    /**
     * Get the start position of this token in the text.
     * @return the position of the first character of this token
     */
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * Get the end position of this token in the text.
     * @return the position just after the last character of this token
     */
    public int getEndPosition() {
        return startPosition + text.length();
    }

}
