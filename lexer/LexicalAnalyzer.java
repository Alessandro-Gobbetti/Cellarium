package lexer;

import java.util.Arrays;

/**
 * A LexicalAnalyzer breaks a String into Tokens.
 * 
 * <pre>
 * lexer.fetchNextToken();
 * Token t1 = lexer.getCurrentToken();
 * </pre> 
 */
public final class LexicalAnalyzer {

    private Token token;
    private String text;
    private int position;
    private TokenFactory[] tokenFactories;

    
    /**
     * Create an analyzer for the given text, 
     * using the given factories to recognize and create tokens.
     * @param expression The text to analyze
     * @param factories The token factories to use
     */
    public LexicalAnalyzer(final String expression, final TokenFactory[] factories) {
        tokenFactories = Arrays.copyOf(factories, factories.length);
        setText(expression);
    }

    /**
     * Create an analyzer for the given text.
     * @param expression The text to analyze
     */
    public LexicalAnalyzer(final String expression) {
        this(expression, new TokenFactory[] {
            new CellReferenceTokenFactory(),
            new FunctionTokenFactory(),
            new LiteralTokenFactory(),
            new OperatorTokenFactory(",", TokenType.COMMA),
            new OperatorTokenFactory(":", TokenType.COLON),
            new OperatorTokenFactory("=", TokenType.EQUAL),
            new OperatorTokenFactory("+", TokenType.PLUS),
            new OperatorTokenFactory("-", TokenType.MINUS),
            new OperatorTokenFactory("*", TokenType.STAR),
            new OperatorTokenFactory("/", TokenType.SLASH),
            new OperatorTokenFactory("%", TokenType.PERCENT),
            new OperatorTokenFactory("(", TokenType.OPEN_PAREN),
            new OperatorTokenFactory(")", TokenType.CLOSED_PAREN),
        });
    }

    /**
     * Provide a new text to analyze.
     * @param expression The text to analyze
     */
    public void setText(final String expression) {
        token = null;
        text = expression;
        position = 0;
        for (final TokenFactory factory : tokenFactories) {
            factory.setText(expression);
        }
    }

    /**
     * Ask the analyzer to move to the next token in the text.
     */
    public void fetchNextToken() {
        token = scanToken();      
    }

    /**
     * Scan the text and extract the next token.
     * @return the next token
     */
    private Token scanToken() {
        skipSpaces();
        if (position == text.length()) {
            return new Token(TokenType.END_OF_FILE, "", position);
        } else {
            int maxLength = 0;
            TokenFactory factoryWithLongestMatch = null;

            // Utilize the tokenFactories to find a factory has the longest match
            for (final TokenFactory factory : tokenFactories) {
                if (factory.find(position)) {
                    if (factory.getTokenLength() > maxLength) {
                        factoryWithLongestMatch = factory;
                        maxLength = factory.getTokenLength();
                    }
                }
            }

            // if no match is found then return null, otherwise produce a token
            if (factoryWithLongestMatch == null) {
                return null;
            } else {
                position += factoryWithLongestMatch.getTokenLength();
                return factoryWithLongestMatch.getToken();
            }
        }
    }
    
    /**
     * to skip the spaces into a text.
     */
    public void skipSpaces() {
        while (position < text.length() && Character.isSpace(text.charAt(position))) {
            ++position;
        }
    }

    /**
     * Get the current token.
     * @return the current token
     */
    public Token getCurrentToken() {
        return token;
    }
    
    /**
     * Get all the input text.
     * @return all the input text.
     */
    public String getText() {
        return text;
    }
    
    /**
     * Returns true if the current token matches the given type.
     * @param tokentype the type to match
     * @return true if the current token matches the given type
     */
    public boolean currentTokenMatches(final TokenType tokentype) {
        boolean result = false;
        if (token != null) {
            result = token.getType() == tokentype;
        }
        return result;
    }
    
    /**
     * Returns true if the current token is invalid.
     * @return true if the current token is invalid
     */
    public boolean currentTokenIsInvalid() {
        return token == null;
    }
    
    /**
     * Returns the name of the current Token.
     * @return the name of the current Token. 
     */
    public String currentTokenName() {
        if (currentTokenIsInvalid()) {
            return "invalid token.";
        } else {
            return token.getType().getName();
        }
    }
}
