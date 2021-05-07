/**
 * A program in a programming language is made up 
 * of different kinds of tokens.
 * This enumeration represents these different kinds.
 */
public enum TokenType {

    IDENTIFIER("identifier"), 

    LITERAL("literal"), 

    PLUS("plus"), 
    MINUS("minus"), 
    STAR("star"), 
    SLASH("slash"), 
    PERCENT("percent"), 

    OPEN_PAREN("open parenthesis"), 
    CLOSED_PAREN("closed parenthesis"),

    END_OF_FILE("end of file"); 

    
    private final String name;
    
    
    /**
     * Initialize a TokenType.
     * @param name The human-readable name of this TokenType.
     */
    private TokenType(final String name) {
        this.name = name;
    }

    /**
     * Get the human-readable name.
     * @return the name of this TokenType.
     */
    public String getName() {
        return name;
    }

}
