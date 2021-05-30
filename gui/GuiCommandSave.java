package gui;

import commands.NotUndoableStateChangingCommand;
import lexer.TokenType;
import spreadsheet.Cell;
import spreadsheet.CellReference;
import spreadsheet.CellariumParser;
import spreadsheet.ImputOutput;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;

/**
 * To save a spreadsheet.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandSave extends NotUndoableStateChangingCommand {
    
    private String sourceCode;
    private SpreadsheetViewTableModel spreadsheetView;
    

    /**
     * Creator for TuiCommandSet.
     * 
     * @param sourceCode the source code
     * @param spreadsheetView the table model
     */
    public GuiCommandSave(final String sourceCode,
                          final SpreadsheetViewTableModel spreadsheetView) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheetView = spreadsheetView;
    }
    
    @Override
    public String getName() {
        return "Save";
    }

    @Override
    public void doit() {
        final CellariumParser parser = new CellariumParser(spreadsheetView.getSpreadsheet());
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
            ImputOutput.save(filePathName, spreadsheetView.getSpreadsheet());
            setLastOperationOk();
        }
    }
    
}
