package spreadsheet;

/**
 * A Parser can convert source code into an AST
 * consisting of Node objects.
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public interface Parser {

    /**
     * Parse the given source code.
     * @param sourceCode The source code of the program
     * @return the AST of the program
     */
    public abstract Node parse(String sourceCode);
    
}
