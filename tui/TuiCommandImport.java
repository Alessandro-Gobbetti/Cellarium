package tui;

import commands.NotUndoableStateChangingCommand;
import lexer.TokenType;
import spreadsheet.CellariumParser;
import spreadsheet.ImputOutput;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;

/**
 * To import a csv file as a spreadsheet.
 * 
 * <p>
 * OPEN         to import a csv file as a spreadsheet.
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandImport extends NotUndoableStateChangingCommand {

    private String sourceCode;
    private Spreadsheet spreadsheet;
    
    /**
     * Creator for TuiCommandSet.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
     */
    public TuiCommandImport(final String sourceCode, final Spreadsheet spreadsheet) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public String getName() {
        return "Import";
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
            ImputOutput.openFromCsv(filePathName, spreadsheet);
        }
    }
}
