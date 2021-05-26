package spreadsheet;

/**
 * Write a description of class NumberCellValue here.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public class NumberCellValue implements CellValue {
    
    private double numberValue;

    /**
     * Constructor for objects of class NumberCellValue.
     * 
     * @param numberValue   value of the Cell
     */
    public NumberCellValue(final double numberValue) {
        super();
        //type = CellType.NUMBER;
        this.numberValue = numberValue;
    }

    @Override
    public CellType type() {
        return CellType.NUMBER;
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
     * Returns the value of the Cell as Number (double).
     * @return numbervalue
     */
    public double asNumber() {
        return numberValue;
    }
    
    /**
     * Returns the value of the Cell as String.
     * @return numbervalue as String
     */
    public String asString() {
        return Double.toString(numberValue);
    }
}
