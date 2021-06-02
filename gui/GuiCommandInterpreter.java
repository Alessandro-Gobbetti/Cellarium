package gui;

import commands.Command;
import commands.CommandProcessor;

import java.awt.Color;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JTextField;

/**
 * Write a description of class SpreadsheetCommandInterpreter here.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandInterpreter {
    
    private HashMap<String, GuiCommandFactory> commandMap;
    private CommandProcessor commandProcessor;
    private JTextField outputMessageField;
    
    public static final String ANSI_BOLD = "\033[0;1m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructor for objects of class SpreadsheetCommandInterpreter.
     */
    public GuiCommandInterpreter() {
        this.commandProcessor = new CommandProcessor();
        this.outputMessageField = null;
        this.commandMap = new HashMap<String, GuiCommandFactory>()
        {
            {
                put("SET", new GuiCommandSetFactory());
                put("CLEAR", new GuiCommandClearFactory());
                put("SAVE", new GuiCommandSaveFactory());
                put("OPEN", new GuiCommandOpenFactory());
                put("IMPORT", new GuiCommandImportFactory());
                put("EXPORT", new GuiCommandExportFactory());
                put("UNDO", new GuiCommandUndoFactory());
                put("REDO", new GuiCommandRedoFactory());
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
        final String commandName = arr.length > 0 ? arr[0].toUpperCase(Locale.getDefault()) : "";
        final String parameters = arr.length > 1 ? arr[1] : "";
        
        final GuiCommandFactory commandFactory = commandMap.get(commandName);
        if (commandFactory == null) {
            printMessage(false, "Please type a valid command!");
        } else {
            final Command command = commandFactory.getCommand(parameters, spreadsheetView);
            // execute the command.
            commandProcessor.doCommand(command);
            printMessage(commandProcessor.wasLastOperationSuccessful(), 
                         commandProcessor.getLastOperationMessage());
        }
    }
    
    /**
     * To get the last operation message.
     * @return the last operation message.
     */
    public String getLastOperationMessage() {
        return commandProcessor.getLastOperationMessage();
    }
    
    /**
     * To set the outputMessageField.
     * 
     * @param outputMessageField the outputMessageField
     */
    public void setOutputMessageField(final JTextField outputMessageField) {
        this.outputMessageField = outputMessageField;
    }
    
    /**
     * To print a message.
     * 
     * @param isOk true if the operation was successfull.
     * @param message the message to print.
     */
    public void printMessage(final boolean isOk, final String message) {
        if (outputMessageField == null) {
            System.out.println(message);
        } else {
            outputMessageField.setForeground(isOk ? Color.GREEN : Color.RED);
            outputMessageField.setText(message);
        }
    }
    
}
