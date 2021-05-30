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
import java.util.HashMap;

/**
 * Write a description of class guiCommandSet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiCommandSave extends NotUndoableStateChangingCommand {
    
    private String sourceCode;
    private SpreadsheetViewTableModel spreadsheetView;
    

    /**
     * Creator for TuiCommandSet.
     */
    public GuiCommandSave(final String sourceCode, final SpreadsheetViewTableModel spreadsheetView) {
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
            ImputOutput.Save(filePathName, spreadsheetView.getSpreadsheet());
            setLastOperationOk();
        }
    }
    
}
