package tui;

import commands.NotUndoableNotStateChangingCommand;
import spreadsheet.Cell;
import spreadsheet.CellReference;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.lexer.TokenType;

/**
 * To print a spreadsheet or just a cell. 
 * 
 * <p>
 * PRINT         to print all the spreadsheet
 * PRINT A1      to print a cell only
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class TuiCommandPrint extends NotUndoableNotStateChangingCommand {
    
    private String sourceCode;
    private Spreadsheet spreadsheet;
    
    /**
     * Creator for TuiCommandPrint.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
     */
    public TuiCommandPrint(final String sourceCode, final Spreadsheet spreadsheet) {
        super();
        this.sourceCode = sourceCode;
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public String getName() {
        return "Print";
    }
    
    @Override
    public void doit() {
        final CellariumParser parser = new CellariumParser(spreadsheet);
        parser.initLexer(sourceCode);
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            PrintSpreadsheetHelper.print(spreadsheet);
            setLastOperationOk();
        } else {
            final Node node = parser.parseCellReference();
            if (!(node instanceof CellReference)) {
                setLastOperationStatus(false, true, "Invalid CellName");
                return;
            }
            final CellReference cellReference = (CellReference) node;
            if (!parser.currentTokenMatches(TokenType.END_OF_FILE)) {
                setLastOperationStatus(false, true, "Reached end of file while parsing");
                return;
            }
            final Cell cell = spreadsheet.getOrCreate(cellReference.getRow(0), 
                                                      cellReference.getCol(0));
            System.out.println(cell.eval().asString());
            setLastOperationOk();
        }
    }
    
}
