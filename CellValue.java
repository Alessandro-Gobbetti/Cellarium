
/**
 * Write a description of class CellValue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CellValue {
    
    enum CellType {EMPTY, STRING, NUMBER, ERROR};
    
    private String stringValue;
    private double numberValue;
    private String errorValue;
    private CellType type;
    
    
    
    public CellValue() {
        type = CellType.EMPTY;
    }
    
    public CellType type() {
        return type;
    }
    
    public double asNumber() {
        //FIXME
        assert(type() == CellType.NUMBER || type() == CellType.EMPTY);
        return type() == CellType.NUMBER ? numberValue : 0.0;
    }
    
    public boolean isConvertibleToNumber() {
        return type() == CellType.NUMBER || type() == CellType.EMPTY;
    }
    
    
    public static CellValue newEmptyCellValue() {
        return new CellValue();
    }
    
    public static CellValue newNumberCellValue(double x) {
        CellValue c = new CellValue();
        c.type = CellType.NUMBER;
        c.numberValue = x;
        return c;
    }
    
    public static CellValue newStringCellValue(String x) {
        CellValue c = new CellValue();
        c.type = CellType.STRING;
        c.stringValue = x;
        return c;
    }
    
    public static CellValue newErrorCellValue(String x) {
        CellValue c = new CellValue();
        c.type = CellType.ERROR;
        c.errorValue = x;
        return c;
    }
}
