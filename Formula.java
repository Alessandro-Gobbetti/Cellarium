import java.util.ArrayList;

/**
 * Write a description of class Formula here.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public abstract class Formula {
    
    /**
     * Returns type of the Cell.
     * @param  spreadsheet  the spreadsheet in which the cell is situated.
     * @return CellValue  the value of the Cell.
     */
    public abstract CellValue eval(Spreadsheet spreadsheet);

    /**
     * Changes the Dependecies.
     * @param  spreadsheet  the spreadsheet in which the cell is situated.
     * @param  list  the ArrayList with the current dependecies.
     */
    public abstract void addDependencies(Spreadsheet spreadsheet, ArrayList<Cell> list);
    
    /**
     * Returns type of the Cell.
     * @param  spreadsheet  the spreadsheet in which the cell is situated.
     * @return the new ArrayList with the Cells who depend from this one.
     */
    public ArrayList<Cell> dependencies(final Spreadsheet spreadsheet) {
        final ArrayList<Cell> result = new ArrayList<Cell>();
        addDependencies(spreadsheet, result);
        return result;
    }

}
