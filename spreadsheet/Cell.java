package spreadsheet;

import java.util.ArrayList;

/**
 * This class defines a Cell.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class Cell {
    
    private int row;
    private int col;
    private CellValue value;
    private boolean isEvaluating;
    private boolean isValueUpToDate;
    private Node formula;
    private ArrayList<Cell> cellDependingOnThis;
    
    
    /**
     * Constructor for objects of class Cell.
     * 
     * @param row the row of the cell.
     * @param col the column of the cell.
     */
    public Cell(final int row, final int col) {
        this.row = row;
        this.col = col;
        this.value = new EmptyCellValue();
        this.isEvaluating = false;
        this.isValueUpToDate = true;
        this.formula = null;
        this.cellDependingOnThis = new ArrayList<Cell>();
    }

    /**
     * Sets the formula of the Cell.
     * @param newFormula  the new formula of the Cell.
     */
    public void setFormula(final Node newFormula) {
        setFormulaAndGetOutdatedCells(newFormula, null);
    }
    
    /**
     * To set the forumula of a cell and store in a list all the outdated cells.
     * @param newFormula the formula to set.
     * @param markedOutOfDate the list of outdated cells.
     */
    public void setFormulaAndGetOutdatedCells(final Node newFormula, 
                                              final ArrayList<Cell> markedOutOfDate) {
        removeDependencies();
        formula = newFormula;
        addDependencies();
        markValueOutOfDate(markedOutOfDate);
    }
    
    /**
     * To get the row of the this cell.
     * @return the row of the this cell.
     */
    public int getRow() {
        return row;
    }
    
    /**
     * To get the column of the this cell.
     * @return the column of the this cell.
     */
    public int getCol() {
        return col;
    }
    
    /**
     * Adds the new Dependencies to the Cell.
     */
    public void addDependencies() {
        if (formula != null) {
            for (final Cell cell: formula.dependencies()) {
                cell.addCellDependingOnThis(this);
            }
        }
    }
    
    /**
     * Removes the Dependencies of the Cell.
     */
    public void removeDependencies() {
        if (formula != null) {
            for (final Cell cell: formula.dependencies()) {
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
     * 
     * @param markedOutOfDate a list of cells that are already marked out of date.
     */
    public void markValueOutOfDate(final ArrayList<Cell> markedOutOfDate) {
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
                    value = formula.eval();
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
        if (formula == null) {
            return "";
        }
        return formula.toString();
    }
    
    /**
     * Return the formula as nodes.
     * @return the formula as nodes.
     */
    public Node getFormulaNode() {
        return formula;
    }
    
    /**
     * Returns the number of cells depending on this.
     * @return the number of cells depending on this.
     */
    public int numberOfCellsDependingOnThis() {
        return cellDependingOnThis.size();
    }

}
