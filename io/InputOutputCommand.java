package io;

import commands.NotUndoableStateChangingCommand;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;
import spreadsheet.lexer.TokenType;

/**
 * Abstract class InputOutputCommand.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public abstract class InputOutputCommand extends NotUndoableStateChangingCommand  {
   
    private boolean isSaveOrOpen;
    private String sourceCode;
    private Spreadsheet spreadsheet;

    /**
     * Creator for InputOutputCommand.
     * 
     * @param isSaveOrOpen  true to save or open a file, false to export or import it.
     * @param sourceCode   the name of the file
     * @param spreadsheet  the spreadsheet 
     */
    public InputOutputCommand(final boolean isSaveOrOpen, 
                              final String sourceCode, 
                              final Spreadsheet spreadsheet) {
        super();
        this.isSaveOrOpen = isSaveOrOpen;
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public abstract String getName();
    
    protected boolean isSaveOrOpen() {
        return isSaveOrOpen;
    }
    
    @Override
    public void doit() {
        final CellariumParser cellariumParser = new CellariumParser(spreadsheet);
        cellariumParser.initLexer(sourceCode);
        if (cellariumParser.currentTokenMatches(TokenType.END_OF_FILE)) {
            setLastOperationStatus(false, true, "Please insert a file name");
        } else {
            final Node fileNameTextNode = cellariumParser.parseCell();
            final String filePathName = fileNameTextNode.toString();
            if (fileNameTextNode.isError() || !(fileNameTextNode instanceof Text)) {
                setLastOperationStatus(false, true, filePathName);
            } else {
                execute(filePathName, spreadsheet);
            }
        }
    }
    
    /**
     * To finally execute the command. 
     * This method expects that the filePathName is valid.
     * 
     * @param filePathName the path of the file.
     * @param spreadsheet the spreadsheet.
     */
    protected abstract void execute(final String filePathName, final Spreadsheet spreadsheet);
}
