import java.util.ArrayList;

/**
 * Write a description of class Cell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cell {
    
    private Spreadsheet owner;
    private int row;
    private int col;
    
    private CellValue value;
    private boolean isEvaluating;
    private boolean isValueUpToDate;
    private Formula formula;
    private Style style;
    private ArrayList<Cell> cellDependingOnThis;
    
    
    /**
     * Constructor for objects of class Cell
     */
    public Cell(final Spreadsheet owner,final int row,final int col) {
        this.owner = owner;
        this.row = row;
        this.col = col;
        this.value = new EmptyCellValue();
        this.isEvaluating = false;
        this.isValueUpToDate = true;
        this.style = new Style();
        this.cellDependingOnThis = new ArrayList<Cell>();
    }

    
    public void setFormula(final Formula newFormula) {
        removeDependencies();
        formula = newFormula;
        addDependencies();
        markValueOutOfDate();
    }
    
    
    public void addDependencies() {
        if (formula != null) {
            for (final Cell cell: formula.dependencies(owner)) {
                cell.addCellDependingOnThis(this);
            }
        }
    }
    
    public void removeDependencies() {
        if (formula != null) {
            for (final Cell cell: formula.dependencies(owner)) {
                cell.removeCellDependingOnThis(this);
            }
        }
    }
    
    
    public void addCellDependingOnThis(final Cell cell) {
        if (!cellDependingOnThis.contains(cell)) {
            cellDependingOnThis.add(cell);
        }
    }
    
    public void removeCellDependingOnThis(final Cell cell) {
        cellDependingOnThis.remove(cell);
    }
    
    public void markValueOutOfDate() {
        if (isValueUpToDate) {
            isValueUpToDate = false;
            for (final Cell c : cellDependingOnThis) {
                c.markValueOutOfDate();
            }
        }
    }
    
    
    public CellValue eval() {
        if (isEvaluating) {
            // Loop detected!
            value = new ErrorCellValue("LOOP");
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
    
}
