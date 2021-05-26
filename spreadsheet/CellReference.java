package spreadsheet;

import java.util.ArrayList;

/**
 * A Cell Reference
 *
 * @author Alessandro Gobbetti
 * @version 2021.05.12
 */
public class CellReference extends Node {
    private boolean rowIsConstant;
    private int row;
    private boolean colIsConstant;
    private int col;

    /**
     * Constructor for CellReference.
     * @param rowIsConstant true if the row is constant: i.e. A$1
     * @param row the row to look at
     * @param colIsConstant true if the column is constant: i.e. $A1 
     * @param col the column to look at
     */
    public CellReference(final boolean rowIsConstant, final int row,
                         final boolean colIsConstant, final int col) {
        super();
        this.rowIsConstant = rowIsConstant;
        this.row = row;
        this.colIsConstant = colIsConstant;
        this.col = col;
    }
    
    /**
     * To convert from Alpha26 notation to integer.
     * @param codeAlpha26 the string to convert
     * @return a integer corresponding to the given Alpha26 number
     */
    public static int fromAlpha26(final String codeAlpha26) {
        int result = 0;
        final int codeLenght = codeAlpha26.length();
        // looping the given string code from left to right
        for (int k = 0; k < codeLenght; ++k) {
            final char c = codeAlpha26.charAt(k);
            if (Character.isDigit(c)) {
                break;
            }
            // Character.getNumericValue() returns the values
            //  10-35 for the letter A-Z
            final int digit = Character.getNumericValue(c) - 9;
            // digits are multiplied by 26^0, 26^1, ...
            result = result * 26 + digit;
        }
        // ALPHA-26 count colum A as the 1st column, we want to have 0 index.
        return result - 1;
    }
    
    /**
     * To convert from column integer to Alpha26 notation.
     * @param col the column integer to convert
     * @return a Alpha26 number corresponding to the given column input
     */
    public static String toAlpha26(final int col) {
        // ALPHA-26 count colum A as the 1st column, we want to have 0 index.
        int alpha26Col = col + 1;
        String result = "";
        // we extract a digit at a time until we have no remainder.
        while (alpha26Col > 0) {
            int currentDigit = alpha26Col % 26;
            if (currentDigit == 0) {
                currentDigit = 26;
            }
            alpha26Col = (alpha26Col - currentDigit) / 26;
            // convert the digit to the letters A, B, C, ...
            final char colChar = (char)(currentDigit + (int)('A') - 1);
            // add digit from right to left
            result = colChar + result;
        }
        return result;
    }
    
    /**
     * Returns a cell index in A1 style.
     * 
     * @param rowIsConstant true if the row is constant: i.e. A$1
     * @param row the row
     * @param colIsConstant true if the column is constant: i.e. $A1 
     * @param col the column
     * 
     * @return a cell index in A1 style.
     */
    public static String toA1(final boolean rowIsConstant, final int row,
                              final boolean colIsConstant, final int col) {
        String result = "";
        if (colIsConstant) { 
            result = result + "$";
        }
        result = result + toAlpha26(col);
        if (rowIsConstant) { 
            result = result + "$";
        }
        // ALPHA-26 count colum A as the 1st column, we want to have 0 index.
        // We also count row 0 as the first column.
        result = result + (row + 1);
        return result;
    }
    
    
    @Override
    public boolean isConstant() {
        return false;
    }
    
    @Override
    public String toString() {
        return toA1(rowIsConstant, row, colIsConstant, col);
        // String result = "";
        // if (colIsConstant) {
            // result = result + "$";
        // }
        // result = result + toAlpha26(col);
        // if (rowIsConstant) {
            // result = result + "$";
        // }
        // // ALPHA-26 count colum A as the 1st column, we want to have 0 index.
        // // We also count row 0 as the first column.
        // final int alpha26Row = row + 1;
        // result = result + alpha26Row;
        // return result;
    }

    /**
     * To get the reference row.
     * @param baseRow the row of the given cell
     * @return the row we are looking for
     */
    public int getRow(final int baseRow) {
        return rowIsConstant ? row : baseRow + row;
    }
    
    /**
     * To get the reference column.
     * @param baseCol the column of the given cell
     * @return the column we are looking for
     */
    public int getCol(final int baseCol) {
        return colIsConstant ? col : baseCol + col;
    }
    
    @Override
    public CellValue eval(final Spreadsheet spreadsheet) {
        return spreadsheet.getValue(row, col);
    }
    
    @Override
    public void addDependencies(final Spreadsheet spreadsheet, final ArrayList<Cell> list) {
        final Cell cell = spreadsheet.getOrCreate(row,col);
        if (!list.contains(cell)) {
            list.add(cell);
        }
    }

}
