
/**
 * Write a description of class ConstFormula here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConstFormula extends Formula {

    double c;
    
    @Override
    CellValue eval(Spreadsheet spreadsheet) {
        return CellValue.newNumberCellValue(c);
    }
}
