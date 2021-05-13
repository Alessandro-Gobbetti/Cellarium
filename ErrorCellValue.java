/**
 * Write a description of class ErrorCellValue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ErrorCellValue implements CellValue{
    
    private String errorValue;

    /**
     * Constructor for objects of class EmptyCellValue
     */
    public ErrorCellValue(String errorValue) {
        super();
        //type = CellType.NUMBER;
        this.errorValue= errorValue;
    }

    @Override
    public CellType type() {
        return CellType.ERROR;
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
    
    
    public String asString() {
        return errorValue;
    }
}
