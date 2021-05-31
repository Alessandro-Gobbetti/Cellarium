package gui;

import commands.Command;
import commands.CommandProcessor;

import java.util.HashMap;

/**
 * Write a description of class SpreadsheetCommandInterpreter here.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandInterpreter {
    
    private HashMap<String, GuiCommandFactory> commandMap;
    private CommandProcessor commandProcessor;
    public static final String ANSI_BOLD = "\033[0;1m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructor for objects of class SpreadsheetCommandInterpreter.
     */
    public GuiCommandInterpreter() {
        commandProcessor = new CommandProcessor();
        commandMap = new HashMap<String, GuiCommandFactory>()
        {
            {
                put("SET", new GuiCommandSetFactory());
                // put("PRINT", new TuiCommandPrintFactory());
                put("CLEAR", new GuiCommandClearFactory());
                put("SAVE", new GuiCommandSaveFactory());
                put("OPEN", new GuiCommandOpenFactory());
                put("IMPORT", new GuiCommandImportFactory());
                put("EXPORT", new GuiCommandExportFactory());
                put("UNDO", new GuiCommandUndoFactory());
                put("REDO", new GuiCommandRedoFactory());
                // put("EXIT", new TuiCommandExitFactory());
                // put("HISTORY", new TuiCommandHistoryFactory(TuiCommandInterpreter.this));
                // put("HELP", new TuiCommandHelpFactory(TuiCommandInterpreter.this));
            }
        };
    }
    
    /**
     * To parse the given source code and execute it on a given spreadsheet.
     * @param sourceCode the code to parse.  
     * @param spreadsheetView the given spreadsheet model.
     */
    public void parseAndExecute(final String sourceCode,
                                final SpreadsheetViewTableModel spreadsheetView) {
        //remove spaces at the beginning or at the end
        final String trimmedSourceCode = sourceCode.trim();
        // split the first world to the rest
        final String[] arr = trimmedSourceCode.split(" ", 2);
        final String commandName = arr.length > 0 ? arr[0].toUpperCase() : "";   // command
        final String parameters = arr.length > 1 ? arr[1] : "";    // command parameters
        
        final GuiCommandFactory commandFactory = commandMap.get(commandName);
        if (commandFactory == null) {
            
            
            //FIXME
            System.out.println("Please insert a valid command!");
        } else {
            final Command command = commandFactory.getCommand(parameters, spreadsheetView);
            // execute the command.
            commandProcessor.doCommand(command);
            if (!commandProcessor.wasLastOperationSuccessful()) {
                
                
                //FIXME
                System.out.println(commandProcessor.getLastOperationMessage());
            }
        }
    }
    
    /**
     * Print a detailed description for the given command.
     * @param commandName the command to return a help.
     */
    public void helpCommand(final String commandName) {
        final GuiCommandFactory command = commandMap.get(commandName.toUpperCase());
        if (command == null) {
            System.out.println(commandName + ": invalid command.");
        } else {
            System.out.println(commandName + ": " + command.helpShort());
            System.out.println(command.helpLong(commandName.toUpperCase()));
        }
    }

    /**
     * Print a short description for all the possible commands.
     */
    public void helpCommandList() {
        System.out.println("Type \"HELP\" followed by a command for more information.");
        // iterate for all element in commandMap
        for (final HashMap.Entry<String, GuiCommandFactory> entry : commandMap.entrySet()) {
            System.out.println(ANSI_BOLD + entry.getKey() + ANSI_RESET + ": " 
                               + entry.getValue().helpShort());
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
