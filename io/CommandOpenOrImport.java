package io;

import spreadsheet.Spreadsheet;

/**
 * To open or import a cellarium or csv file as a spreadsheet.
 * 
 * <p>
 * OPEN         to open a cellarium file as a spreadsheet.
 * IMPORT       to import a csv file as a spreadsheet.
 * </p>
 * 
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class CommandOpenOrImport extends InputOutputCommand {

    /**
     * Creator for TuiCommandOpenOrImport.
     * 
     * @param isOpen  true to open a file, false to import it.
     * @param sourceCode   the sourceCode 
     * @param spreadsheet  the spreadsheet 
     */
    public CommandOpenOrImport(final boolean isOpen, 
                               final String sourceCode, 
                               final Spreadsheet spreadsheet) {
        super(isOpen, sourceCode, spreadsheet);
    }
    
    @Override
    public String getName() {
        return isSaveOrOpen() ? "Open" : "Import";
    }
    
    @Override
    protected void execute(final String filePathName, final Spreadsheet spreadsheet) {
        if (isSaveOrOpen()) {
            InputOutputHelper.open(filePathName, spreadsheet);
        } else {
            InputOutputHelper.openFromCsv(filePathName, spreadsheet);
        }
        setLastOperationOk();
    }
}
