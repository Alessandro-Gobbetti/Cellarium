/**
 * Write a description of class ErrorCellValue here.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public class ErrorCellValue implements CellValue {
    
    private String errorValue;

    /**
     * Constructor for objects of class EmptyCellValue.
     * @param errorValue  the Error message.
     */
    public ErrorCellValue(final String errorValue) {
        super();
        //type = CellType.NUMBER;
        this.errorValue = errorValue;
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
    
    /**
     * Rerurns the Error message for the Error Cell.
     * @return errorValue the Error message.
     */
    public String asString() {
        return errorValue;
    }
}
