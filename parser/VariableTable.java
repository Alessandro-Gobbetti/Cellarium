package parser;

import java.util.HashMap;
import java.util.Map;


/**
 * A table of variables
 * (a map from variable names to variable values).
 */
public class VariableTable {
    
    private final Map<String,Number> variables;
    
    
    /**
     * Create a new empty variable table.
     */
    public VariableTable() {
        variables = new HashMap<String,Number>();
    }
    
    /**
     * Get the value of the variable with the given name.
     * @param name The name of the variable.
     * @return The value of the variable with the given name.
     */
    public Number get(final String name) {
        return variables.get(name);
    }
    
    /**
     * Set the value of the variable with the given name.
     * @param name The name of the variable.
     * @param value The new value of the variable.
     */
    public void set(final String name, final Number value) {
        variables.put(name, value);
    }    
}