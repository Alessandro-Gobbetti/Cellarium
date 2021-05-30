package spreadsheet;

/**
 * Write a description of class EmptyCellValue here.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class EmptyCellValue implements CellValue {
    
    @Override
    public CellType type() {
        return CellType.EMPTY;
    }
    
    @Override
    public boolean isConvertibleToNumber() {
        return true;
    }
    
    @Override
    public boolean isError() {
        return false;
    }
    
    /**
     * Returns zero for an empty Cell.
     * @return the Cell as Number (double).
     */
    public double asNumber() {
        return 0.0;
    }
    
    /**
     * Returns an empty String for an empty Cell.
     * @return the Cell as String.
     */
    public String asString() {
        return "";
    }
}
