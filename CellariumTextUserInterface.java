import java.util.Scanner;

/**
 * Write a description of class CellariumTextUserInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CellariumTextUserInterface {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BOLD ="\033[0;1m";
    private static boolean terminated = false;
    public static void main(final String[] args) {
        System.out.println("Cellarium 1.0 (Jun 04 2021)");
        System.out.println("Type \"HELP\" for more information.");
        final Spreadsheet spreadsheet = new Spreadsheet();
        SpreadsheetCommandInterpreter interpreter = new SpreadsheetCommandInterpreter();
        // scan the input 
        final Scanner scanner = new Scanner(System.in);
        while(!terminated) {
            System.out.print(ANSI_BOLD + ANSI_RED + "Cellarium> " + ANSI_RESET);
            String command = scanner.nextLine();
            boolean success = interpreter.parseAndExecute(command, spreadsheet);
            if (!success) {
                System.out.println("Please insert a valid command");
            }
        }
    }
    
    public static void setTerminated(final boolean terminated) {
        CellariumTextUserInterface.terminated = terminated;
    }
}
