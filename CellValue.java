
/**
 * Write a description of class CellValue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface CellValue {
       
    public CellType type();
    
    public boolean isConvertibleToNumber();
    
    public double asNumber();
    
    public String asString();
}
