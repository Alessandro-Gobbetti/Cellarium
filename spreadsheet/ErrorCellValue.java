package spreadsheet;

/**
 * This class defines the values of an error Cell.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class ErrorCellValue implements CellValue {
    
    private String code;
    private String text;

    /**
     * Constructor for objects of class EmptyCellValue.
     * @param code        the code of the Error message.
     * @param text        the text of the Error message.
     */
    public ErrorCellValue(final String code, final String text) {
        super();
        this.code = code;
        this.text = text;
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
    public boolean isError() {
        return true;
    }
    
    @Override
    public double asNumber() {
        return Double.NaN;
    }
    
    /**
     * Rerurns the Error message for the Error Cell.
     * @return the Error message.
     */
    public String asString() {
        return code;
    }
    
    /**
     * Rerurns the Error detailed message for the Error Cell.
     * @return the Error message.
     */
    public String getMessage() {
        return text;
    }
}
