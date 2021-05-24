package spreadsheet;

import java.util.ArrayList;

/**
 * Write a description of class Cell here.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public class Cell {
    
    private Spreadsheet owner;
    private int row;
    private int col;
    private CellValue value;
    private boolean isEvaluating;
    private boolean isValueUpToDate;
    private Node formula;
    private Style style;
    private ArrayList<Cell> cellDependingOnThis;
    
    
    /**
     * Constructor for objects of class Cell.
     * 
     * @param owner  the owner of the Cell.
     */
    public Cell(final Spreadsheet owner, final int row, final int col) {
        this.owner = owner;
        this.row = row;
        this.col = col;
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
        setFormulaAndGetOutdatedCells(newFormula, null);
    }
    
    public void setFormulaAndGetOutdatedCells(final Node newFormula, ArrayList<Cell> markedOutOfDate) {
        removeDependencies();
        formula = newFormula;
        addDependencies();
        markValueOutOfDate(markedOutOfDate);
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
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
    public void markValueOutOfDate(ArrayList<Cell> markedOutOfDate) {
        if (isValueUpToDate) {
            if (markedOutOfDate != null) {
                markedOutOfDate.add(this);
            }
            isValueUpToDate = false;
            for (final Cell c : cellDependingOnThis) {
                c.markValueOutOfDate(markedOutOfDate);
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
    
    /**
     * To return the formula as string.
     * 
     * @return the formula as string.
     */
    public String getFormula() {
        return formula.toString();
    }
    
    public int numberOfCellsDependingOnThis() {
        return cellDependingOnThis.size();
    }

}
