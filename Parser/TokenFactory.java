/**
 * A TokenFactory can produce Tokens.
 */
public class TokenFactory {

    /**
     * Set the text in which to look for tokens.
     * @param text The text to scan for tokens
     */
    public void setText(final String text) {
        // Do nothing. To be overridden in subclasses.
    }

    /**
     * Look for tokens in the text, starting from the given position.
     * @param startFrom The position in the text at which to start looking
     * @return true of the factory can produce a token from the given position
     */
    public boolean find(final int startFrom) {
        // Do nothing. To be overridden in subclasses.
        return false;
    }

    /**
     * Get the length of the token we found at the start position in the text.
     * @return The length (in number of characters)
     */
    public int getTokenLength() {
        // Do nothing. To be overridden in subclasses.
        return -1;
    }

    /**
     * Get the token we found at the start position in the text.
     * @return The token
     */
    public Token getToken() {
        // Do nothing. To be overridden in subclasses.
        return null;
    }
    
}
