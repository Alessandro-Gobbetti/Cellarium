package io;

import commands.NotUndoableStateChangingCommand;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;
import spreadsheet.lexer.TokenType;

/**
 * To save or export a cellarium file as a spreadsheet.
 * 
 * <p>
 * SAVE         to save a cellarium file as a spreadsheet.
 * EXPORT       to export a csv file as a spreadsheet.
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class CommandSaveOrExport extends NotUndoableStateChangingCommand {

    private boolean isSave;
    private String sourceCode;
    private Spreadsheet spreadsheet;
    

    /**
     * Creator for CommandSaveOrExport.
     * 
     * @param isSave  true to save a file, false to export it.
     * @param sourceCode   the name of the file
     * @param spreadsheet  the spreadsheet 
     */
    public CommandSaveOrExport(final boolean isSave, 
                               final String sourceCode, 
                               final Spreadsheet spreadsheet) {
        super();
        this.isSave = isSave;
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public String getName() {
        return isSave ? "Save" : "Export";
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
                return;
            } else {
                if (isSave) {
                    InputOutputHelper.save(filePathName, spreadsheet);
                } else {
                    InputOutputHelper.generateCsvFile(filePathName, spreadsheet);
                }
                setLastOperationOk();
            }
        }
    }
    
}
