package tui;

import lexer.TokenType;
import spreadsheet.CellariumParser;
import spreadsheet.Node;
import spreadsheet.Spreadsheet;
import spreadsheet.Text;

/**
 * To save the spreadsheet into a csv file.
 * 
 * <p>
 * SAVE         to save the spreadsheet into a csv file
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class SpreadsheetCommandSave implements SpreadsheetCommand {

    @Override
    public boolean parseAndExecute(final String sourceCode, final Spreadsheet spreadsheet) {
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
            ManageCsv.generateCsvFile(filePathName, spreadsheet);
        }
        return true;
    }
    
    @Override
    public String helpShort() {
        return "Save the spreadsheet ";
    }
    
    @Override
    public String helpLong(final String commandName) {
        return commandName + " FILE-PATH: save the spreadsheet in csv into the given directory.";
    }
}
