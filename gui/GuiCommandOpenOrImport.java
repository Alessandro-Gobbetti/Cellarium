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
public class GuiCommandOpenOrImport extends NotUndoableNotStateChangingCommand {
    
    private boolean isOpen;
    private String sourceCode;
    private SpreadsheetViewTableModel spreadsheetView;
    

    /**
     * Creator for GuiCommandOpenOrImport.
     * 
     * @param isOpen true to open a file, false to import it.
     * @param sourceCode the source code
     * @param spreadsheetView the table model
     */
    public GuiCommandOpenOrImport(final boolean isOpen,
                                  final String sourceCode,
                                  final SpreadsheetViewTableModel spreadsheetView) {
        super();
        this.isOpen = isOpen;
        this.sourceCode = sourceCode;
        this.spreadsheetView = spreadsheetView;
    }
    
    @Override
    public String getName() {
        return isOpen ? "Open" : "Import";
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
                return;
            }
            if (isOpen) {
                InputOutputHelper.open(filePathName, spreadsheetView.getSpreadsheet());
            } else {
                InputOutputHelper.openFromCsv(filePathName,
                                              spreadsheetView.getSpreadsheet());
            }
            spreadsheetView.fireTableDataChanged();
            setLastOperationOk();
        }
    }
    
}
