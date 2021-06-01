package tui;

import commands.NotUndoableStateChangingCommand;
import lexer.TokenType;
import spreadsheet.CellariumParser;
import spreadsheet.InputOutputHelper;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;

/**
 * To open a cellarium file as a spreadsheet.
 * 
 * <p>
 * OPEN         to open a cellarium file as a spreadsheet.
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandOpen extends NotUndoableStateChangingCommand {

    private String sourceCode;
    private Spreadsheet spreadsheet;
    

    /**
     * Creator for TuiCommandOpen.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
     */
    public TuiCommandOpen(final String sourceCode, final Spreadsheet spreadsheet) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public String getName() {
        return "Open";
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
            InputOutputHelper.open(filePathName, spreadsheet);
            setLastOperationOk();
        }
    }
    
}
