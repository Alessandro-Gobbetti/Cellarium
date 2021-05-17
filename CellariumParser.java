import parser.*;

/**
 * A Parser for our Arith language
 * (a simple language of arithmetic expressions).
 * 
 * <code>
 * CELL         ::= Literal | 
 *                  Text |
 *                  [ "=" ] EXPRESSION
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal |
 *                  Identifier | 
 *                  Cell reference |
 *                  "(" EXPRESSION ")"
 * </code>
 */
public final class CellariumParser implements Parser {
    
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
        // now parse the CELL
        return parseCell();
    }
    
    
    /**
     * Parse a Cell
     * This assumes the lexer already points to the first token of this expression.
     * 
     * <p>EBNF:
     * <code>
     * CELL ::= Literal |
     *          Text |
     *          [ "=" ] EXPRESSION
     * </code>
     * 
     */
    private Node parseCell() {
        if (lexer.currentTokenMatches(TokenType.EQUAL)
            || lexer.currentTokenMatches(TokenType.PLUS)) {
            lexer.fetchNextToken();
            final Node expression = parseExpression();
            if (!expression.isError() && !lexer.currentTokenMatches(TokenType.END_OF_FILE)) {
                return new Error("Err:Syntax", "Syntax error: garbage after the expression");
            }
            return expression;
        } else if (lexer.currentTokenMatches(TokenType.LITERAL)) {
            final Node literal = new Literal(Double.parseDouble(lexer.getCurrentToken().getText()));
            lexer.fetchNextToken();
            if (lexer.currentTokenMatches(TokenType.END_OF_FILE)) {
                return literal;
            } else {
                return new Text(lexer.getText());
            }
        } else {
            return new Text(lexer.getText());
        }
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
        if (lexer.currentTokenMatches(TokenType.PLUS)) {
            lexer.fetchNextToken();
        } else if (lexer.currentTokenMatches(TokenType.MINUS)) {
            shouldNegate = true;
            lexer.fetchNextToken();
        }
               
        // parse TERM
        Node term = parseTerm();
        if (term.isError()) {
            // term is malformed and parseTerm() has already returned an error message
            return term;
        }
        
        // apply negation
        if (shouldNegate) {
            term = new Negation(term);
        }
        
        // next token fetched in parseTerm()
        
        while (lexer.currentTokenMatches(TokenType.PLUS)
               || lexer.currentTokenMatches(TokenType.MINUS)) {
            final boolean shouldAdd = lexer.currentTokenMatches(TokenType.PLUS);
            lexer.fetchNextToken();
            final Node currentTerm = parseTerm();
            //next token already fetched in parseTerm()
            if (currentTerm.isError()) {
                // currentTerm is malformed and parseTerm() has already returned an error message
                return currentTerm;
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
        if (factor.isError()) {
            // factor is malformed and parseFactor() has already returned an error message
            return factor;
        }
        
        while (lexer.currentTokenMatches(TokenType.STAR)
               || lexer.currentTokenMatches(TokenType.SLASH)) {
            final boolean shouldMul = lexer.currentTokenMatches(TokenType.STAR);
            lexer.fetchNextToken();
            final Node currentFactor = parseFactor();
            //next token already fetched in parseFactor()
            if (currentFactor.isError()) {
                // currentFactor is malformed and parseFactor()
                // has already returned an error message
                return currentFactor;
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
     *          Cell reference |
     *          "(" EXPRESSION ")"
     * </code>
     * 
     * @return a Node representing the factor
     */
    private Node parseFactor() {
        if (lexer.currentTokenMatches(TokenType.LITERAL)) {
            final Node factor = new Literal(Double.parseDouble(lexer.getCurrentToken().getText()));
            lexer.fetchNextToken();
            // produce Node
            return factor;
        } else if (lexer.currentTokenMatches(TokenType.FUNCTION)) {
            final Node factor = new Variable(lexer.getCurrentToken().getText());
            lexer.fetchNextToken();
            // produce Node
            return factor;
        } else if (lexer.currentTokenMatches(TokenType.CELLREFERENCE)) {
            // parse the cell reference and produce Node
            return parseCellReference();
        } else if (lexer.currentTokenMatches(TokenType.OPEN_PAREN)) {
            // skip the parenthesis
            lexer.fetchNextToken();
            // parse the EXPRESSION
            final Node factor = parseExpression();
            if (factor.isError()) {
                // factor is malformed and parseExpression()
                // has already returned an error message
                return factor;
            }
            // skip the closed parenthesis
            if (lexer.currentTokenMatches(TokenType.CLOSED_PAREN)) {
                lexer.fetchNextToken();
                return factor;
            } else {
                // return error message
                return new Error("Err:Syntax",
                                 "Syntax error: expected a ')', got " + lexer.currentTokenName());
            }
        } else {
            // print error message
            return new Error("Err:Syntax",
                             "Syntax error: expected a FACTOR, got " + lexer.currentTokenName());
        }
    }
    
    /**
     * Parse a cell reference.
     * This assumes the lexer already points to the first token of this factor.
     * 
     * <p>EBNF:
     * <code>
     * CELLREFERENCE ::= "[$]?[A-Z]+[$]?[0-9]+"
     * </code>
     * 
     * @return a Node representing the factor
     */
    private Node parseCellReference() {
        if (!lexer.currentTokenMatches(TokenType.CELLREFERENCE)) {
            return new Error("Err:Syntax",
                             "Expected a CELL REFERENCE, got " + lexer.currentTokenName());
        }
        // Get the reference string before skipping it.
        String reference = lexer.getCurrentToken().getText();
        lexer.fetchNextToken();
        
        // Separate reference in row and column parts.
        int startOfColIndex = 0;
        int endOfColIndex = 0;
        int startOfRowIndex = 0;
        int endOfRowIndex = reference.length();
        boolean rowIsConstant = false;
        boolean colIsConstant = false;
        // skip initial dollars
        if (reference.charAt(startOfColIndex) == '$') {
            colIsConstant = true;
            startOfColIndex++;
        }
        // step over column name chars until first digit (or dollars) for row number.
        boolean found = false;
        for (int i = startOfColIndex; i < endOfRowIndex && !found; i++) {
            char ch = reference.charAt(i);
            if (ch == '$') {
                rowIsConstant = true;
                startOfRowIndex = i + 1;
                endOfColIndex = i;
                found = true;
            } else if (Character.isDigit(ch)) {
                startOfRowIndex = i;
                endOfColIndex = i;
                found = true;
            }
        }
        if (!found) {
            return new Error("Err:Syntax", "Malformed CELL REFERENCE, got " + reference);
        }
        String colString = reference.substring(startOfColIndex, endOfColIndex);
        String rowString = reference.substring(startOfRowIndex, endOfRowIndex);
        // ALPHA-26 count colum A as the 1st column, we want to have 0 index.
        // We also count row 0 as the first column.
        int row = Integer.parseInt(rowString) - 1;
        // ALPHA-26 number format
        int col = CellReference.fromAlpha26(colString);
        
        if (row < 0 || col < 0) {
            return new Error("Err:REF",
                             "Cell out of range " + reference
                                         + " row: '" + rowString + "' -> " + row
                                         + " col: '" + colString + "' -> " + col);
        }
        return new CellReference(rowIsConstant, row, colIsConstant, col);
    }

}
