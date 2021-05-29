package tui;

import spreadsheet.Spreadsheet;

import java.util.Scanner;

/**
 * To run a TUI version of Cellarium
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class CellariumTui {
    
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BOLD = "\033[0;1m";
    
    private static boolean terminate = false;
    
    /**
     * Constructor for CellariumTui.
     */
    private CellariumTui() {
    }
    
    /**
     * Main method to run a TUI version of Cellarium.
     * @param args the arguments list
     */
    public static void main(final String[] args) {
        System.out.println("Cellarium 1.0 (Jun 04 2021)");
        System.out.println("Type \"HELP\" for more information.");
        final Spreadsheet spreadsheet = new Spreadsheet();
        final SpreadsheetCommandInterpreter interpreter = new SpreadsheetCommandInterpreter();
        // scan the input 
        final Scanner scanner = new Scanner(System.in);
        while (!terminate) {
            System.out.print(ANSI_BOLD + ANSI_RED + "Cellarium> " + ANSI_RESET);
            final String command = scanner.nextLine();
            interpreter.parseAndExecute(command, spreadsheet);
            // if (!success) {
                // System.out.println("Please insert a valid command");
            // }
        }
    }
    
    /**
     * Set the terminated boolean: false to stop the program execution.
     * @param terminate  the new value of this.terminate: 
     *                   false to stop the program execution, true to contine.
     */
    public static void setTerminated(final boolean terminate) {
        CellariumTui.terminate = terminate;
    }
}
