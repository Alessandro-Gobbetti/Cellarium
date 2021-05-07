/**
 * A Parser can convert source code into an AST
 * consisting of Node objects.
 */
public interface Parser {

    /**
     * Parse the given source code.
     * @param sourceCode The source code of the program
     * @return the AST of the program
     */
    public abstract Node parse(String sourceCode);
    
}
