package spreadsheet;

import lexer.LexicalAnalyzer;
import lexer.TokenType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Parser for our Cellaium language
 * (a simple language of arithmetic expressions).
 * 
 * <code>
 * CELL         ::= Literal | 
 *                  Text |
 *                  [ "=" ] EXPRESSION
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" ) FACTOR }
 * FACTOR       ::= Literal |
 *                  FUNCTION | 
 *                  Cell reference |
 *                  "(" EXPRESSION ")"
 * FUNCTION     ::= FUNCTION_NAME parameters ")"  
 *               
 * </code>
 */
public final class CellariumParser implements Parser {
    
    private LexicalAnalyzer lexer;
    private HashMap<String, FunctionNodeCreator> functionMap;
    
    /**
     * Constructor for CellariumParser. To initialize a map with all functions.
     */
    public CellariumParser() {
        functionMap = new HashMap<String, FunctionNodeCreator>() 
        {
            {
                put("PI(", new LiteralNodeCreator(Math.PI));
                put("SIN(", new SineNodeCreator());
                put("COS(", new CosineNodeCreator());
                put("AVERAGE(", new AverageNodeCreator());
                put("SUM(", new SumNodeCreator());
                put("COUNT(", new CountNodeCreator());
                put("MIN(", new MinimumNodeCreator());
                put("MAX(", new MaximumNodeCreator());
            }
        };
    }

    /**
     * To Init the LexicalAnlayzer.
     * @param sourceCode The source code of the program in the Cellaium language
     */
    public void initLexer(final String sourceCode) {
        this.lexer = new LexicalAnalyzer(sourceCode);
        // fetch first token
        lexer.fetchNextToken();
    }
    
    /**
     * Parse a program in the Cellarium language.
     * @param sourceCode The source code of the program in the Cellaium language
     * @return an AST of the program
     */
    public Node parse(final String sourceCode) {
        // to init the Lexical Analyzer
        initLexer(sourceCode);
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
     * @return a Node representing the expression
     */
    public Node parseCell() {
        if (currentTokenMatches(TokenType.EQUAL)
            || currentTokenMatches(TokenType.PLUS)
            || currentTokenMatches(TokenType.MINUS)) {
            // if we got a minus we should parse it in parseExpression
            // = 4 +-3 is allowed.
            if (!currentTokenMatches(TokenType.MINUS)) {
                lexer.fetchNextToken();
            }
            final Node expression = parseExpression();
            if (!expression.isError() && !currentTokenMatches(TokenType.END_OF_FILE)) {
                return new Error("Err:Syntax", "Syntax error: garbage after the expression");
            }
            return expression;
        } else if (currentTokenMatches(TokenType.LITERAL)) {
            final Node literal = new Literal(Double.parseDouble(lexer.getCurrentToken().getText()));
            lexer.fetchNextToken();
            if (currentTokenMatches(TokenType.END_OF_FILE)) {
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
        if (currentTokenMatches(TokenType.PLUS)) {
            lexer.fetchNextToken();
        } else if (currentTokenMatches(TokenType.MINUS)) {
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
        
        while (currentTokenMatches(TokenType.PLUS)
               || currentTokenMatches(TokenType.MINUS)) {
            final boolean shouldAdd = currentTokenMatches(TokenType.PLUS);
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
        
        while (currentTokenMatches(TokenType.STAR)
               || currentTokenMatches(TokenType.SLASH)) {
            final boolean shouldMul = currentTokenMatches(TokenType.STAR);
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
     *          Cell reference |
     *          Cell range |
     *          "(" EXPRESSION ")"
     * </code>
     * 
     * @return a Node representing the factor
     */
    private Node parseFactor() {
        if (currentTokenMatches(TokenType.LITERAL)) {
            final Node factor = new Literal(Double.parseDouble(lexer.getCurrentToken().getText()));
            lexer.fetchNextToken();
            // produce Node
            return factor;
        } else if (currentTokenMatches(TokenType.FUNCTION)) {
            // produce Node
            return parseFunction();
        } else if (currentTokenMatches(TokenType.CELLREFERENCE)) {
            Node reference = parseCellReference();
            if (reference.isError()) {
                return reference;
            }
            if (!currentTokenMatches(TokenType.COLON)) {
                return reference;
            } else {
                // skip the colon
                lexer.fetchNextToken();
                Node secondReference = parseCellReference();
                if (secondReference.isError()) {
                    return secondReference;
                }
                // cast nodes to CellReferences, we know it's possible because there are not errors.
                final CellReference begin = (CellReference)reference;
                final CellReference end = (CellReference)secondReference;
                return new CellReferenceRange(begin, end);
            }
        } else if (currentTokenMatches(TokenType.OPEN_PAREN)) {
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
            if (currentTokenMatches(TokenType.CLOSED_PAREN)) {
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
    public Node parseCellReference() {
        if (!currentTokenMatches(TokenType.CELLREFERENCE)) {
            return new Error("Err:Syntax",
                             "Expected a CELL REFERENCE, got " + lexer.currentTokenName());
        }
        // Get the reference string before skipping it.
        final String reference = lexer.getCurrentToken().getText();
        lexer.fetchNextToken();
        
        // Separate reference in row and column parts.
        int startOfColIndex = 0;
        int endOfColIndex = 0;
        int startOfRowIndex = 0;
        final int endOfRowIndex = reference.length();
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
            final char ch = reference.charAt(i);
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
        final String colString = reference.substring(startOfColIndex, endOfColIndex);
        final String rowString = reference.substring(startOfRowIndex, endOfRowIndex);
        // ALPHA-26 count colum A as the 1st column, we want to have 0 index.
        // We also count row 0 as the first column.
        final int row = Integer.parseInt(rowString) - 1;
        // ALPHA-26 number format
        final int col = CellReference.fromAlpha26(colString);
        
        if (row < 0 || col < 0) {
            return new Error("Err:REF",
                             "Cell out of range " + reference
                                         + " row: '" + rowString + "' -> " + row
                                         + " col: '" + colString + "' -> " + col);
        }
        return new CellReference(rowIsConstant, row, colIsConstant, col);
    }

    private Node parseFunction() {
        if (!currentTokenMatches(TokenType.FUNCTION)) {
            return new Error("Err:Syntax",
                             "Expected a FUNCTION, got " + lexer.currentTokenName());
        }
        // Get the reference string before skipping it.
        final String functionName = lexer.getCurrentToken().getText().toUpperCase();
        lexer.fetchNextToken();
        final FunctionNodeCreator functionNodeCreator = functionMap.get(functionName);
        if (functionNodeCreator == null) {
            return new Error("Err:Syntax", "Unknown function, got " + functionName);
        }
        final ArrayList<Node> parameters = parseParameters();
        if (parameters == null) {
            return new Error("Err:Syntax", "Parse parameters error");
        } else if (parameters.size() > 0 && parameters.get(0).isError()) {
            return parameters.get(0);
        } else if (!currentTokenMatches(TokenType.CLOSED_PAREN)) {
            return new Error("Err:Syntax",
                             "Syntax error: expected a ')', got " + lexer.currentTokenName());
        }
        // skip the closed parenthesis
        lexer.fetchNextToken();
        //create node
        return functionNodeCreator.create(parameters);
    }
    
    /**
     * returns empty list if no paramters,
     * list with one element if there are errors,
     * otherwise, a list with all the parameters.
     */
    private ArrayList<Node> parseParameters() {
        final ArrayList<Node> result = new ArrayList<Node>();
        if (!currentTokenMatches(TokenType.CLOSED_PAREN)) {
            Node parameter = parseExpression();
            if (parameter.isError()) {
                result.clear();
                result.add(parameter);
                return result;
            }
            result.add(parameter);
            while (currentTokenMatches(TokenType.COMMA)) {
                //skip comma
                lexer.fetchNextToken();
                parameter = parseExpression();
                if (parameter.isError()) {
                    result.clear();
                    result.add(parameter);
                    return result;
                }
                result.add(parameter);
            }
        }
        return result;
    }
    
    /**
     * To compare token types.
     * @param type the given token type.
     * @return true if the current token matches the given token type.
     */
    public boolean currentTokenMatches(final TokenType type) {
        return lexer != null && lexer.currentTokenMatches(type);
    }
}
