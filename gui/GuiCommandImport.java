package gui;

import commands.NotUndoableStateChangingCommand;
import lexer.TokenType;
import spreadsheet.CellariumParser;
import spreadsheet.InputOutputHelper;
import spreadsheet.Node;
import spreadsheet.Text;

/**
 * Write a description of class guiCommandSet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandImport extends NotUndoableStateChangingCommand {
    
    private String sourceCode;
    private SpreadsheetViewTableModel spreadsheetView;
    

    /**
     * Creator for GuiCommandImport.
     * 
     * @param sourceCode the source code
     * @param spreadsheetView the table model.
     */
    public GuiCommandImport(final String sourceCode,
                            final SpreadsheetViewTableModel spreadsheetView) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheetView = spreadsheetView;
    }
    
    @Override
    public String getName() {
        return "Import";
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
            InputOutputHelper.openFromCsv(filePathName, spreadsheetView.getSpreadsheet());
            spreadsheetView.fireTableDataChanged();
            setLastOperationOk();
        }
    }
    
}
