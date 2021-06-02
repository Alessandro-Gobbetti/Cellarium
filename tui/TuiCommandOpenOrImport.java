package tui;

import commands.NotUndoableStateChangingCommand;
import spreadsheet.CellariumParser;
import spreadsheet.InputOutputHelper;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;
import spreadsheet.lexer.TokenType;

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
public class TuiCommandOpenOrImport extends NotUndoableStateChangingCommand {

    private boolean isOpen;
    private String sourceCode;
    private Spreadsheet spreadsheet;
    

    /**
     * Creator for TuiCommandOpenOrImport.
     * 
     * @param isOpen  true to open a file, false to import it.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
     */
    public TuiCommandOpenOrImport(final boolean isOpen, 
                                  final String sourceCode, 
                                  final Spreadsheet spreadsheet) {
        super();
        this.isOpen = isOpen;
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public String getName() {
        return isOpen ? "Open" : "Import";
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
            if (isOpen) {
                InputOutputHelper.open(filePathName, spreadsheet);
            } else {
                InputOutputHelper.openFromCsv(filePathName, spreadsheet);
            }
            setLastOperationOk();
        }
    }
    
}
