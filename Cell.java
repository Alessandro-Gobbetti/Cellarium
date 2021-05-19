import java.util.ArrayList;

/**
 * Write a description of class Cell here.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public class Cell {
    
    private Spreadsheet owner;    
    private CellValue value;
    private boolean isEvaluating;
    private boolean isValueUpToDate;
    private Node formula;
    private Style style;
    private ArrayList<Cell> cellDependingOnThis;
    
    
    /**
     * Constructor for objects of class Cell.
     * Sets all fields.
     * @param owner  the owner of the Cell.
     * @param row    the row of the Cell.
     * @param col    the column of the Cell.
     */
    public Cell(final Spreadsheet owner) {
        this.owner = owner;
        this.value = new EmptyCellValue();
        this.isEvaluating = false;
        this.isValueUpToDate = true;
        this.style = new Style();
        this.cellDependingOnThis = new ArrayList<Cell>();
    }

    /**
     * Sets the formula of the Cell.
     * @param newFormula  the new formula of the Cell.
     */
    public void setFormula(final Node newFormula) {
        removeDependencies();
        formula = newFormula;
        addDependencies();
        markValueOutOfDate();
    }
    
    /**
     * Adds the new Dependencies to the Cell.
     */
    public void addDependencies() {
        if (formula != null) {
            for (final Cell cell: formula.dependencies(owner)) {
                cell.addCellDependingOnThis(this);
            }
        }
    }
    
    /**
     * Removes the Dependencies of the Cell.
     */
    public void removeDependencies() {
        if (formula != null) {
            for (final Cell cell: formula.dependencies(owner)) {
                cell.removeCellDependingOnThis(this);
            }
        }
    }
    
    /**
     * Adds to this Cell Dependencies the given Cell. 
     * @param cell  a certain Cell.
     */
    public void addCellDependingOnThis(final Cell cell) {
        if (!cellDependingOnThis.contains(cell)) {
            cellDependingOnThis.add(cell);
        }
    }
    
    /**
     * Removes a certiain Cell form the Dependencies of this Cell.
     * @param cell  a certain Cell.
     */
    public void removeCellDependingOnThis(final Cell cell) {
        cellDependingOnThis.remove(cell);
    }
    
    /**
     * Marks that the Cell and his Dependencies are not Updated.
     */
    public void markValueOutOfDate() {
        if (isValueUpToDate) {
            isValueUpToDate = false;
            for (final Cell c : cellDependingOnThis) {
                c.markValueOutOfDate();
            }
        }
    }
    
    /**
     * Evaluates the CellValue of the Cell.
     * @return CellValue  the value of the Cell.
     */
    public CellValue eval() {
        if (isEvaluating) {
            // Loop detected!
            value = new ErrorCellValue("Err:LOOP", "The formula calls a cell depending on this.");
        } else {
            isEvaluating = true;
            if (!isValueUpToDate) {
                if (formula == null) {
                    value = new EmptyCellValue();
                } else {
                    value = formula.eval(owner);
                }
                isValueUpToDate = true;
            }
            isEvaluating = false;
        }
        return value;
    }
    
    public String getFormula() {
        return formula.toString();
    }
}
