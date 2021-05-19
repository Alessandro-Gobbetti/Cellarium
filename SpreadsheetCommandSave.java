import parser.*;

/**
 * Write a description of class SpreadsheetSet here.
 * 
 * SAVE         to print all the spreadsheet
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetCommandSave implements SpreadsheetCommand{

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
            
            ConvertToCsv.generateCsvFile(filePathName, spreadsheet);
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
