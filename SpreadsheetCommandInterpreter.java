import java.util.HashMap;

/**
 * Write a description of class SpreadsheetCommandInterpreter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetCommandInterpreter {
    
    private HashMap<String, SpreadsheetCommand> commandMap;
    public static final String ANSI_BOLD ="\033[0;1m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructor for objects of class SpreadsheetCommandInterpreter
     */
    public SpreadsheetCommandInterpreter() {
        commandMap = new HashMap<String, SpreadsheetCommand>() {{
            put("SET", new SpreadsheetCommandSet());
            put("PRINT", new SpreadsheetCommandPrint());
            put("CLEAR", new SpreadsheetCommandClear());
            put("SAVE", new SpreadsheetCommandSave());
            put("OPEN", new SpreadsheetCommandOpen());
            put("EXIT", new SpreadsheetCommandExit());
            put("HELP", new SpreadsheetCommandHelp(SpreadsheetCommandInterpreter.this));
        }};
    }
    
    public boolean parseAndExecute(final String sourceCode, final Spreadsheet spreadsheet) {
        //remove spaces at the beginning or at the end
        final String trimmedSourceCode = sourceCode.trim();
        // split the first world to the rest
        final String arr[] = sourceCode.split(" ", 2);
        final String commandName = arr.length > 0 ? arr[0] : "";   // command
        final String parameters = arr.length > 1 ? arr[1] : "";    // command parameters
        final SpreadsheetCommand command = commandMap.get(commandName);
        if (command == null) {
            return false;
        }
        return command.parseAndExecute(parameters, spreadsheet);       
    }
    
    public void helpCommand(final String commandName) {
        final SpreadsheetCommand command = commandMap.get(commandName);
        if (command == null) {
            System.out.println(commandName + ": invalid command.");
        } else {
            System.out.println(commandName + ": " + command.helpShort());
            System.out.println(command.helpLong(commandName));
        }
    }

    public void helpCommandList() {
        System.out.println("Type \"HELP\" followed by a command for more information.");
        // iterate for all element in commandMap
        for (final HashMap.Entry<String, SpreadsheetCommand> entry : commandMap.entrySet()) {
            System.out.println(ANSI_BOLD + entry.getKey() + ANSI_RESET + ": " + entry.getValue().helpShort());
        }
    }
}
