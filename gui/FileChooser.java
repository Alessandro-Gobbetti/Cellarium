package gui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A file FileChooser. This class extends JFileChooser that 
 * provides a simple mechanism for the user to choose a file.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class FileChooser extends JFileChooser {

    private static final FileNameExtensionFilter CELLARIUM_FILTER = 
        new FileNameExtensionFilter("Cellarium files (*.cellarium)",
                                     "cellarium");
    private static final FileNameExtensionFilter CSV_FILTER =
        new FileNameExtensionFilter("Text files (*.csv, *.txt)",
                                    "txt", "text", "csv");

    /**
     * To create and show a open file dialog.
     * @param interpreter the interpreter.
     * @param spreadsheetView the table view model.
     */
    public void openFileDialog(final GuiCommandInterpreter interpreter, 
                               final SpreadsheetViewTableModel spreadsheetView) {
        resetChoosableFileFilters();
        setFileFilter(CELLARIUM_FILTER);
        final int returnVal = showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File file = getSelectedFile();
            interpreter.parseAndExecute("OPEN " + getCurrentDirectory() + "/"  
                                        + getName(file), spreadsheetView);
        }
    }
    
    /**
     * To create and show a save file dialog.
     * @param interpreter the interpreter.
     * @param spreadsheetView the table view model.
     */
    public void saveFileDialog(final GuiCommandInterpreter interpreter, 
                               final SpreadsheetViewTableModel spreadsheetView) {
        resetChoosableFileFilters();
        setFileFilter(CELLARIUM_FILTER);
        final int returnVal = showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File file = getSelectedFile();
            interpreter.parseAndExecute("SAVE " + getCurrentDirectory() + "/"  
                                        + getName(file) + ".cellarium",
                                        spreadsheetView);
        }
    }
    
    /**
     * To create and show a import file dialog.
     * @param interpreter the interpreter.
     * @param spreadsheetView the table view model.
     */
    public void importFileDialog(final GuiCommandInterpreter interpreter,
                                 final SpreadsheetViewTableModel spreadsheetView) {
        resetChoosableFileFilters();
        setFileFilter(CSV_FILTER);
        final int returnVal = showDialog(this, "Import");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File file = getSelectedFile();
            interpreter.parseAndExecute("IMPORT " + getCurrentDirectory() + "/"  
                                        + getName(file), spreadsheetView);
        }
    }
    
    /**
     * To create and show a export file dialog.
     * @param interpreter the interpreter.
     * @param spreadsheetView the table view model.
     */
    public void exportFileDialog(final GuiCommandInterpreter interpreter,
                                 final SpreadsheetViewTableModel spreadsheetView) {
        resetChoosableFileFilters();
        setFileFilter(CSV_FILTER);
        final int returnVal = showDialog(this, "Export");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File file = getSelectedFile();
            interpreter.parseAndExecute("EXPORT " + getCurrentDirectory() 
                                        + "/"  + getName(file) + ".csv", 
                                        spreadsheetView);
        }
    }
}
