package tui;

import commands.NotUndoableStateChangingCommand;
import spreadsheet.lexer.TokenType;
import spreadsheet.CellariumParser;
import spreadsheet.InputOutputHelper;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;

/**
 * To save the spreadsheet into a Cellarium file.
 * 
 * <p>
 * SAVE         to save the spreadsheet into a Cellarium file
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandSave extends NotUndoableStateChangingCommand {
    
    private String sourceCode;
    private Spreadsheet spreadsheet;
    

    /**
     * Creator for TuiCommandSave.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
     */
    public TuiCommandSave(final String sourceCode, final Spreadsheet spreadsheet) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public String getName() {
        return "Save";
    }

    @Override
    public void doit() {
        final CellariumParser parser = new CellariumParser(spreadsheet);
        parser.initLexer(sourceCode);
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            setLastOperationStatus(false, true, "Please insert a file name");
            return;
        } else {
            final Node content = parser.parseCell();
            final String filePathName = content.toString();
            if (content.isError() || !(content instanceof Text)) {
                setLastOperationStatus(false, true, filePathName);
                return;
            }
            InputOutputHelper.save(filePathName, spreadsheet);
            setLastOperationOk();
        }
    }
    
}
