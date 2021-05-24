package spreadsheet;

/**
 * Write a description of class StringCellValue here.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public class StringCellValue implements CellValue {
    
    private String stringValue;

    /**
     * Constructor for objects of class EmptyCellValue.
     * @param stringValue  the value of the String.
     */
    public StringCellValue(final String stringValue) {
        super();
        //type = CellType.NUMBER;
        this.stringValue = stringValue;
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
        return Double.NaN;
    }
    
    /**
     * Returns the value of the Cell as a String.
     * @return sringValue  the value of the Cell.
     */
    public String asString() {
        return stringValue;
    }
}
