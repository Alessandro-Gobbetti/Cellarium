/**
 * Write a description of class NumberCellValue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NumberCellValue implements CellValue{
    
    private double numberValue;

    /**
     * Constructor for objects of class NumberCellValue
     */
    public NumberCellValue(double numberValue) {
        super();
        //type = CellType.NUMBER;
        this.numberValue= numberValue;
    }

    @Override
    public CellType type() {
        return CellType.NUMBER;
    }
    
    @Override
    public boolean isConvertibleToNumber() {
        return true;
    }
    

    public double asNumber() {
        return numberValue;
    }
    
    
}
