package io;

import spreadsheet.Spreadsheet;

/**
 * To save or export a cellarium file as a spreadsheet.
 * 
 * <p>
 * SAVE         to save a cellarium file as a spreadsheet.
 * EXPORT       to export a csv file as a spreadsheet.
 * </p>
 * 
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class CommandSaveOrExport extends InputOutputCommand {

    /**
     * Creator for CommandSaveOrExport.
     * 
     * @param isSave  true to save a file, false to export it.
     * @param sourceCode   the name of the file
     * @param spreadsheet  the spreadsheet 
     */
    public CommandSaveOrExport(final boolean isSave, 
                               final String sourceCode, 
                               final Spreadsheet spreadsheet) {
        super(isSave, sourceCode, spreadsheet);
    }
    
    @Override
    protected void execute(final String filePathName, final Spreadsheet spreadsheet) {
        if (isSaveOrOpen()) {
            InputOutputHelper.save(filePathName, spreadsheet);
        } else {
            InputOutputHelper.generateCsvFile(filePathName, spreadsheet);
        }
        setLastOperationOk();
    }
    
    @Override
    public String getName() {
        return isSaveOrOpen() ? "Save" : "Export";
    }
}
