/**
 * Write a description of class EmptyCellValue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EmptyCellValue implements CellValue{
    
    
    /**
     * Constructor for objects of class EmptyCellValue
     */
    public EmptyCellValue() {
        super();
    }

    @Override
    public CellType type() {
        return CellType.EMPTY;
    }
    
    @Override
    public boolean isConvertibleToNumber() {
        return true;
    }
    

    public double asNumber() {
        return 0.0;
    }
    
    public String asString() {
        return "";
    }
}
