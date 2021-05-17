import java.util.Scanner;

/**
 * Write a description of class CellariumTextUserInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CellariumTextUserInterface {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BOLD ="\033[0;1m";
    
    public static void main(final String[] args) {
        Spreadsheet spreadsheet = new Spreadsheet();
        SpreadsheetCommandInterpreter interpreter = new SpreadsheetCommandInterpreter();
        // scan the input 
        Scanner scanner = new Scanner(System.in);
        boolean terminated = false;
        while(!terminated) {
            System.out.print(ANSI_BOLD + ANSI_RED + "Cellarium> " + ANSI_RESET);
            String command = scanner.nextLine();
            boolean success = interpreter.parseAndExecute(command, spreadsheet);
            if (!success) {
                System.out.println("Please insert a valid command");
            }
        }
    }
}
