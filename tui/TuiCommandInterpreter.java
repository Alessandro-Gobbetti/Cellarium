package tui;

import commands.Command;
import commands.CommandProcessor;
import spreadsheet.Spreadsheet;

import java.util.HashMap;
import java.util.Locale;

/**
 * Write a description of class SpreadsheetCommandInterpreter here.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandInterpreter {
    
    private HashMap<String, TuiCommandFactory> commandMap;
    private CommandProcessor commandProcessor;
    public static final String ANSI_BOLD = "\033[0;1m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructor for objects of class SpreadsheetCommandInterpreter.
     */
    public TuiCommandInterpreter() {
        commandProcessor = new CommandProcessor();
        commandMap = new HashMap<String, TuiCommandFactory>()
        {
            {
                put("SET", new TuiCommandSetFactory());
                put("PRINT", new TuiCommandPrintFactory());
                put("CLEAR", new TuiCommandClearFactory());
                put("SAVE", new TuiCommandSaveFactory());
                put("OPEN", new TuiCommandOpenFactory());
                put("IMPORT", new TuiCommandImportFactory());
                put("EXPORT", new TuiCommandExportFactory());
                put("UNDO", new TuiCommandUndoFactory());
                put("REDO", new TuiCommandRedoFactory());
                put("EXIT", new TuiCommandExitFactory());
                put("HISTORY", new TuiCommandHistoryFactory(TuiCommandInterpreter.this));
                put("HELP", new TuiCommandHelpFactory(TuiCommandInterpreter.this));
            }
        };
    }
    
    /**
     * To parse the given source code and execute it on a given spreadsheet.
     * @param sourceCode the code to parse.  
     * @param spreadsheet the given spreadsheet.
     */
    public void parseAndExecute(final String sourceCode, final Spreadsheet spreadsheet) {
        //remove spaces at the beginning or at the end
        final String trimmedSourceCode = sourceCode.trim();
        // split the first world to the rest
        final String[] arr = trimmedSourceCode.split(" ", 2);
        final String commandName = arr.length > 0 ? arr[0].toUpperCase(Locale.getDefault()) : "";
        final String parameters = arr.length > 1 ? arr[1] : "";
        
        final TuiCommandFactory commandFactory = commandMap.get(commandName);
        if (commandFactory == null) {
            System.out.println("Please insert a valid command!");
        } else {
            final Command command = commandFactory.getCommand(parameters, spreadsheet);
            // execute the command.
            commandProcessor.doCommand(command);
            if (!commandProcessor.wasLastOperationSuccessful()) {
                System.out.println(commandProcessor.getLastOperationMessage());
            }
        }
    }
    
    /**
     * Print a detailed description for the given command.
     * @param commandName the command to return a help.
     */
    public void helpCommand(final String commandName) {
        final TuiCommandFactory command =
            commandMap.get(commandName.toUpperCase(Locale.getDefault()));
        if (command == null) {
            System.out.println(commandName + ": invalid command.");
        } else {
            System.out.println(commandName + ": " + command.helpShort());
            System.out.println(command.helpLong(commandName.toUpperCase(Locale.getDefault())));
        }
    }

    /**
     * Print a short description for all the possible commands.
     */
    public void helpCommandList() {
        System.out.println("Type \"HELP\" followed by a command for more information.");
        // iterate for all element in commandMap
        for (final HashMap.Entry<String, TuiCommandFactory> entry : commandMap.entrySet()) {
            System.out.println(
                ANSI_BOLD + entry.getKey() + ANSI_RESET + ": " + entry.getValue().helpShort()
            );
        }
    }
    
    /**
     * Print a the command history list.
     */
    public void printCommandHistory() {
        final int pastCount = commandProcessor.getUndoCount();
        if (pastCount == 0) {
            System.out.println("No commands to undo!");
        } else {
            System.out.println("COMMANDS TO UNDO:");
            for (int i = 0; i < pastCount; i++) {
                System.out.println("   " + i + ": " + commandProcessor.getUndoCommandName(i));
            }
        }
        final int futureCount = commandProcessor.getRedoCount();
        if (futureCount == 0) {
            System.out.println("No commands to redo!");
        } else {
            System.out.println("COMMANDS TO REDO:");
            for (int i = 0; i < futureCount; i++) {
                System.out.println("   " + i + ": " + commandProcessor.getRedoCommandName(i));
            }
        }
    }
    
}
