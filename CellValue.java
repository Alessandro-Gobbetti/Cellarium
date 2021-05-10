
/**
 * Write a description of class CellValue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CellValue {

    private String stringValue;
    private double numberValue;
    private String errorValue;
    
    enum CellType {EMPTY, STRING, NUMBER, ERROR};
    
    public static CellType type() {
        // FIXME
        return CellType.NUMBER;
    }
    
    public double asNumber() {
        //FIXME
        assert(type() == CellType.NUMBER || type() == CellType.EMPTY);
        return type() == CellType.NUMBER ? numberValue : 0.0;
    }
    
    
    public static CellValue newEmptyCellValue() {
        return new CellValue();
    }
    
    public static CellValue newNumberCellValue(double x) {
        CellValue c = new CellValue();
        c.numberValue = x;
        return c;
    }
    
    public static CellValue newStringCellValue(String x) {
        CellValue c = new CellValue();
        c.stringValue = x;
        return c;
    }
    
    public static CellValue newErrorCellValue(String x) {
        CellValue c = new CellValue();
        c.errorValue = x;
        return c;
    }
}
