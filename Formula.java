import java.util.ArrayList;

/**
 * Write a description of class Formula here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Formula {
    
    public abstract CellValue eval(Spreadsheet spreadsheet);

    public abstract void addDependencies(Spreadsheet spreadsheet, ArrayList<Cell> list);
    
    public ArrayList<Cell> dependencies(Spreadsheet spreadsheet) {
        ArrayList<Cell> result = new ArrayList<Cell>();
        addDependencies(spreadsheet, result);
        return result;
    }

}
