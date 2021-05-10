
/**
 * Write a description of class ScalingFormula here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ScalingFormula extends Formula {
    
    private double s;
    private int row;
    private int col;
    
    @Override
    public CellValue eval(Spreadsheet spreadsheet) {
        CellValue vRowCol = spreadsheet.getValue(row, col);
        if (vRowCol.type() == CellValue.type()) {
            return CellValue.newNumberCellValue(vRowCol.asNumber()*s);
        } else {
            return CellValue.newErrorCellValue("#VALUE");
        }
    }

}
