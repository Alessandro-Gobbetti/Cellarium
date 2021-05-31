package spreadsheet;

/**
 * This class defines some CellTypes.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public interface CellValue {
    
    /**
     * Returns type of the Cell.
     * @return Type  of the Cell (e.g. NUMBER, EMPTY, STRING, ERROR).
     */
    public abstract CellType type();
    
    /**
     * Returns whether the Cell's value is convertible into a Number.
     * @return boolean
     */
    public abstract boolean isConvertibleToNumber();
    
    /**
     * Returns true if the cell contains an error.
     * @return true if the cell contains an error.
     */
    public abstract boolean isError();
        
    /**
     * Returns the value of the Cell as Number (double).
     * @return numbervalue
     */
    public abstract double asNumber();
    
    /**
     * Returns the value of the Cell as String.
     * @return numbervalue as String
     */
    public abstract String asString();
}
