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
    private ArrayList<Cell> CellDependingOnThis;
    
    
    /**
     * Constructor for objects of class Cell
     */
    public Cell(Spreadsheet owner, int row, int col) {
        this.owner = owner;
        this.row = row;
        this.col = col;
        isEvaluating = false;
        isValueUpToDate = true;
        this.style = new Style();
        this.CellDependingOnThis = new ArrayList<Cell>();
    }

    
    public void setFormula(Formula newFormula) {
        removeDependencies();
        formula = newFormula;
        addDependencies();
        markValueOutOfDate();
    }
    
    
    public void addDependencies() {
        if (formula != null) {
            for (Cell cell: formula.dependencies(owner)) {
                cell.addCellDependingOnThis(this);
            }
        }
    }
    
    public void removeDependencies() {
        if (formula != null) {
            for (Cell cell: formula.dependencies(owner)) {
                cell.removeCellDependingOnThis(this);
            }
        }
    }
    
    
    public void addCellDependingOnThis(Cell cell) {
        if (!CellDependingOnThis.contains(cell)) {
            CellDependingOnThis.add(cell);
        }
    }
    
    public void removeCellDependingOnThis(Cell cell) {
        CellDependingOnThis.remove(cell);
    }
    
    public void markValueOutOfDate() {
        if (isValueUpToDate) {
            isValueUpToDate = false;
            for (Cell c : CellDependingOnThis) {
                c.markValueOutOfDate();
            }
        }
    }
    
    
    public CellValue eval() {
        if (isEvaluating) {
            // Loop detected!
            value = CellValue.newErrorCellValue("LOOP");
        } else {
            isEvaluating = true;
            if (!isValueUpToDate) {
                if (formula == null) {
                    value = CellValue.newEmptyCellValue();
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
