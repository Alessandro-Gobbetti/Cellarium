import java.util.ArrayList;

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
    
    public ScalingFormula(final double s, final int row, final int col) {
        super();
        this.s = s;
        this.row = row;
        this.col = col;
    }
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        final CellValue vRowCol = spreadsheet.getValue(row, col);
        if (vRowCol.isConvertibleToNumber()) {
            return new NumberCellValue(vRowCol.asNumber()*s);
        } else {
            return new ErrorCellValue("#VALUE");
        }
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        final Cell cell = spreadsheet.getOrCreate(row,col);
        if(!list.contains(cell)) {
            list.add(cell);
        }
    }
    
}
