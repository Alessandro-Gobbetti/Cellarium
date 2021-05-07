/**
 * A Parser for our Arith language
 * (a simple language of arithmetic expressions).
 * 
 * <code>
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal | 
 *                  Identifier| 
 *                  "(" EXPRESSION ")"
 * </code>
 */
public final class ArithParser implements Parser {
    
    private LexicalAnalyzer lexer;

    
    /**
     * Parse a program in the Arith language.
     * @param sourceCode The source code of the program in the Arith language
     * @return an AST of the program
     */
    public Node parse(final String sourceCode) {
        this.lexer = new LexicalAnalyzer(sourceCode);
        // fetch first token
        lexer.fetchNextToken();
        // now parse the EXPRESSION
        return parseExpression();
    }
    
    /**
     * Parse an expression.
     * This assumes the lexer already points to the first token of this expression.
     * 
     * <p>EBNF:
     * <code>
     * EXPRESSION ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
     * </code>
     * 
     * @return a Node representing the expression
     */
    private Node parseExpression() {
        // parse [ "+" | "-" ]
        boolean shouldNegate = false;
        if (lexer.getCurrentToken().getType() == TokenType.PLUS) {
            lexer.fetchNextToken();
        } else if (lexer.getCurrentToken().getType() == TokenType.MINUS) {
            shouldNegate = true;
            lexer.fetchNextToken();
        }
               
        // parse TERM
        Node term = parseTerm();
        if (term == null) {
            // term is malformed and parseTerm() has already printed an error message
            return null;
        }
        
        // apply negation
        if (shouldNegate) {
            term = new Negation(term);
        }
        
        // next token fetched in parseTerm()
        
        while (lexer.getCurrentToken().getType() == TokenType.PLUS
               || lexer.getCurrentToken().getType() == TokenType.MINUS) {
            final boolean shouldAdd = lexer.getCurrentToken().getType() == TokenType.PLUS;
            lexer.fetchNextToken();
            final Node currentTerm = parseTerm();
            //next token already fetched in parseTerm()
            if (currentTerm == null) {
                // currentTerm is malformed and parseTerm() has already printed an error message
                return null;
            }
            if (shouldAdd) {
                term = new Addition(term, currentTerm);
            } else {
                term = new Subtraction(term, currentTerm);
            }
        }
        return term;
    }
    
    /**
     * Parse a term.
     * This assumes the lexer already points to the first token of this term.
     * 
     * <p>EBNF:
     * <code>
     * TERM ::= FACTOR { ( "*" | "/" ) FACTOR }
     * </code>
     * 
     * @return a Node representing the term
     */
    private Node parseTerm() {
        Node factor = parseFactor();
        // next already token fetched in parseFactor()
        if (factor == null) {
            // factor is malformed and parseFactor() has already printed an error message
            return null;
        }
        
        while (lexer.getCurrentToken().getType() == TokenType.STAR 
               || lexer.getCurrentToken().getType() == TokenType.SLASH) {
            final boolean shouldMul = lexer.getCurrentToken().getType() == TokenType.STAR;
            lexer.fetchNextToken();
            final Node currentFactor = parseFactor();
            //next token already fetched in parseFactor()
            if (currentFactor == null) {
                // currentFactor is malformed and parseFactor() has already printed an error message
                return null;
            }
            if (shouldMul) {
                factor = new Multiplication(factor, currentFactor);
            } else {
                factor = new Division(factor, currentFactor);
            }
            
        }
        return factor;   
    }
    
    /**
     * Parse a factor.
     * This assumes the lexer already points to the first token of this factor.
     * 
     * <p>EBNF:
     * <code>
     * FACTOR ::=  
     *          Literal | 
     *          Identifier | 
     *          "(" EXPRESSION ")"
     * </code>
     * 
     * @return a Node representing the factor
     */
    private Node parseFactor() {
        if (lexer.getCurrentToken().getType() == TokenType.LITERAL) {
            final Node factor = new Literal(Integer.parseInt(lexer.getCurrentToken().getText()));
            lexer.fetchNextToken();
            // produce Node
            return factor;
        } else if (lexer.getCurrentToken().getType() == TokenType.IDENTIFIER) {
            final Node factor = new Variable(lexer.getCurrentToken().getText());
            lexer.fetchNextToken();
            // produce Node
            return factor;
        } else if (lexer.getCurrentToken().getType() == TokenType.OPEN_PAREN) {
            // skip the parenthesis
            lexer.fetchNextToken();
            // parse the EXPRESSION
            final Node factor = parseExpression();
            if (factor == null) {
                // factor is malformed and parseExpression() has already printed an error message
                return null;
            }
            // skip the closed parenthesis
            if (lexer.getCurrentToken().getType() == TokenType.CLOSED_PAREN) {
                lexer.fetchNextToken();
                return factor;
            } else {
                // print error message
                System.out.println("Expected a ')', got " + lexer.getCurrentToken().getType());
                return null;
            }
        } else {
            // print error message
            System.out.println("Expected a FACTOR, got " + lexer.getCurrentToken().getType());
            return null;
        }
    }

}
