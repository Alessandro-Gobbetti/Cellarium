package tui;

import lexer.TokenType;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;

/**
 * To open a csv file as a spreadsheet.
 * 
 * <p>
 * OPEN         to open a csv file as a spreadsheet.
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetCommandOpen implements SpreadsheetCommand {

    @Override
    public boolean parseAndExecute(final String sourceCode,final Spreadsheet spreadsheet) {
        final CellariumParser parser = new CellariumParser();
        parser.initLexer(sourceCode);
        if (parser.currentTokenMatches(TokenType.END_OF_FILE)) {
            System.out.println("Please insert a file name");
            return false;
        } else {
            final Node content = parser.parseCell();
            final String filePathName = content.toString();
            if (content.isError() || !(content instanceof Text)) {
                System.out.println(filePathName);
                return false;
            }
            ManageCsv.openFromCsv(filePathName, spreadsheet);
        }
        return true;
    }
    
    @Override
    public String helpShort() {
        return "Open a spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: open the spreadsheet from a csv file.";
    }
}
