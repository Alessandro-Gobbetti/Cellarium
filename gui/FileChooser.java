package gui;
import javax.swing.LookAndFeel;
import javax.swing.JFileChooser;
import java.awt.Color;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Container;
import java.awt.Component;
import javax.swing.UIManager;;
import java.io.File;


/**
 * Write a description of class FileChooser here.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class FileChooser extends JFileChooser{

    
    public void openFileDialog(final GuiCommandInterpreter interpreter, final SpreadsheetViewTableModel spreadsheetView) {
        final int returnVal = showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = getSelectedFile();
            interpreter.parseAndExecute("SAVE " + getCurrentDirectory() + "/"  + getName(file), spreadsheetView);
        }
    }
    
    public void saveFileDialog(final GuiCommandInterpreter interpreter, final SpreadsheetViewTableModel spreadsheetView) {
        int returnVal = showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = getSelectedFile();
            interpreter.parseAndExecute("SAVE " + getCurrentDirectory() + "/"  + getName(file) + ".cellarium", spreadsheetView);
        }
    }
    
    public void importFileDialog(final GuiCommandInterpreter interpreter, final SpreadsheetViewTableModel spreadsheetView) {
        final int returnVal = showDialog(this, "Import");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = getSelectedFile();
            interpreter.parseAndExecute("IMPORT " + getCurrentDirectory() + "/"  + getName(file), spreadsheetView);
        }
    }
    
    public void exportFileDialog(final GuiCommandInterpreter interpreter, final SpreadsheetViewTableModel spreadsheetView) {
        int returnVal = showDialog(this, "Export");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = getSelectedFile();
            interpreter.parseAndExecute("EXPORT " + getCurrentDirectory() + "/"  + getName(file) + ".csv", spreadsheetView);
        }
    }
}
