/**
 * Write a description of class StringCellValue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StringCellValue implements CellValue{
    
    private String stringValue;

    /**
     * Constructor for objects of class EmptyCellValue
     */
    public StringCellValue(String stringValue) {
        super();
        //type = CellType.NUMBER;
        this.stringValue= stringValue;
    }

    @Override
    public CellType type() {
        return CellType.STRING;
    }
    
    @Override
    public boolean isConvertibleToNumber() {
        return false;
    }
    
    @Override
    public double asNumber() {
        //FIXME
        return Double.NaN;
    }
}
