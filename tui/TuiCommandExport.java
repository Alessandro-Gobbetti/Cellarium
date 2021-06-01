package tui;

import commands.NotUndoableStateChangingCommand;
import lexer.TokenType;
import spreadsheet.CellariumParser;
import spreadsheet.InputOutputHelper;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;

/**
 * To export the spreadsheet into a csv file.
 * 
 * <p>
 * SAVE         to export the spreadsheet into a csv file
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandExport extends NotUndoableStateChangingCommand {

    private String sourceCode;
    private Spreadsheet spreadsheet;
    
    /**
     * Creator for TuiCommandExport.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
     */
    public TuiCommandExport(final String sourceCode, final Spreadsheet spreadsheet) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public String getName() {
        return "Export";
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
            InputOutputHelper.generateCsvFile(filePathName, spreadsheet);
            setLastOperationOk();
        }
    }
}
