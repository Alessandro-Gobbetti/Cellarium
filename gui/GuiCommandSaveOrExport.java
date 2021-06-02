package gui;

import commands.NotUndoableNotStateChangingCommand;
import spreadsheet.CellariumParser;
import spreadsheet.InputOutputHelper;
import spreadsheet.Node;
import spreadsheet.Text;
import spreadsheet.lexer.TokenType;

/**
 * To save a spreadsheet.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class GuiCommandSaveOrExport extends NotUndoableNotStateChangingCommand {
    
    private boolean isSave;
    private String sourceCode;
    private SpreadsheetViewTableModel spreadsheetView;
    

    /**
     * Creator for GuiCommandSaveOrExport.
     * 
     * @param isSave true to save a file, false to export it.
     * @param sourceCode the source code
     * @param spreadsheetView the table model
     */
    public GuiCommandSaveOrExport(final boolean isSave,
                                  final String sourceCode,
                                  final SpreadsheetViewTableModel spreadsheetView) {
        super();
        this.isSave = isSave;
        this.sourceCode = sourceCode;
        this.spreadsheetView = spreadsheetView;
    }
    
    @Override
    public String getName() {
        return isSave ? "Save" : "Export";
    }

    @Override
    public void doit() {
        final CellariumParser parser = new CellariumParser(spreadsheetView.getSpreadsheet());
        parser.initLexer(sourceCode);
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            setLastOperationStatus(false, true, "Please insert a file name");
        } else {
            final Node content = parser.parseCell();
            final String filePathName = content.toString();
            if (content.isError() || !(content instanceof Text)) {
                setLastOperationStatus(false, true, filePathName);
            } else {
                if (isSave) {
                    InputOutputHelper.save(filePathName, spreadsheetView.getSpreadsheet());
                } else {
                    InputOutputHelper.generateCsvFile(filePathName,
                                                      spreadsheetView.getSpreadsheet());
                }
                setLastOperationOk();
            }
        }
    }
    
}
