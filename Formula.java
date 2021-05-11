import java.util.ArrayList;

/**
 * Write a description of class Formula here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Formula {
    
    CellValue eval(Spreadsheet spreadsheet) {
        // implemented in subclasses
        return new CellValue();
    }

    
    public ArrayList<Cell> dependencies(Spreadsheet spreadsheet) {
        ArrayList<Cell> result = new ArrayList<Cell>();
        return result;
    }
}
