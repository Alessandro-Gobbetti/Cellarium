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
    
    public ScalingFormula(double s, int row, int col) {
        this.s = s;
        this.row = row;
        this.col = col;
    }
    
    @Override
    public CellValue eval(Spreadsheet spreadsheet) {
        CellValue vRowCol = spreadsheet.getValue(row, col);
        if (vRowCol.type() == CellValue.type()) {
            return CellValue.newNumberCellValue(vRowCol.asNumber()*s);
        } else {
            return CellValue.newErrorCellValue("#VALUE");
        }
    }
    
    @Override
    public ArrayList<Cell> dependencies(Spreadsheet spreadsheet) {
        ArrayList<Cell> result = new ArrayList<Cell>();
        Cell cell = spreadsheet.getOrCreate(row,col);
        result.add(cell);
        return result;
    }
}
