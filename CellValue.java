
/**
 * Write a description of class CellValue here.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public interface CellValue {
    
    /**
     * Returns type of the Cell.
     * @return Type  of the Cell (e.g. NUMBER, EMPTY, STRING, ERROR).
     */
    public CellType type();
    
    /**
     * Returns whether the Cell's value is convertible into a Number.
     * @return boolean
     */
    public boolean isConvertibleToNumber();
    
    /**
     * Returns the value of the Cell as Number (double).
     * @return numbervalue
     */
    public double asNumber();
    
    /**
     * Returns the value of the Cell as String.
     * @return numbervalue as String
     */
    public String asString();
}
