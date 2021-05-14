import java.util.ArrayList;

/**
 * Write a description of class ConstFormula here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConstFormula extends Formula {

    private double c;
    
    public ConstFormula(final double c) {
        super();
        this.c = c;
    }
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        return new NumberCellValue(c);
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        //FIXME
    }
}
