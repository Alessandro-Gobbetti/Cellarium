import java.util.ArrayList;

/**
 * Write a description of class ConstFormula here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConstFormula extends Formula {

    double c;
    
    public ConstFormula(double c) {
        this.c = c;
    }
    
    @Override
    CellValue eval(Spreadsheet spreadsheet) {
        return CellValue.newNumberCellValue(c);
    }
    
    @Override
    public void addDependencies(Spreadsheet spreadsheet, ArrayList<Cell> list) {
    }
}
