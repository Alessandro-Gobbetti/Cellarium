
/**
 * To print a spreadsheet on terminal.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PrintSpreadsheet {

    
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    
    /**
     * To print a spreadsheet on terminal.
     * @param spreadsheet the spreadsheet to print.
     */
    public static void print(final Spreadsheet spreadsheet) {
        final int maxCol = spreadsheet.getMaxUsedCellCol();
        final int maxRow = spreadsheet.getMaxUsedCellRow();
        //print top border
        System.out.print("┌───────┬");
        for (int col = 0; col < maxCol; col++) {
            System.out.print("───────┬");
        }
        System.out.println("───────┐");
        //print column names
        System.out.print("│\t");
        for (int col = 0; col <= maxCol; col++) {
            System.out.print("│   " + ANSI_RED + CellReference.toAlpha26(col) + "\t" + ANSI_RESET );
        }
        System.out.println("│");
        //print content
        for (int row = 0; row <= maxRow; row++) {
            System.out.print("│" + ANSI_RED + (row + 1) + "\t" + ANSI_RESET);
            for (int col = 0; col <= maxCol; col++) {
                System.out.print("│");
                if (spreadsheet.exists(row, col)) {
                    System.out.print(spreadsheet.getValue(row,col).asString());    
                }
                System.out.print("\t");
            }
            System.out.println("│");
        }
        //print botton border
        System.out.print("└───────┴");
        for (int col = 0; col < maxCol; col++) {
            System.out.print("───────┴");
        }
        System.out.println("───────┘");
    }
        
}
