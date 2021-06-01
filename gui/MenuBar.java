package gui;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * The class MenuBar creates the MenuBar for the GUI of the Spreadsheet.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class MenuBar extends JMenuBar {
    
    private GuiCommandInterpreter interpreter;
    private SpreadsheetViewTableModel spreadsheetView;
    private FileChooser fileChooser;
    private final FileNameExtensionFilter cellariumFilter;
    private final FileNameExtensionFilter csvFilter;
    
    /**
     * Creates the a MenuBar.
     * @param font the font of the MenuBar.
     * @param interpreter the command intepreter
     * @param spreadsheetView the view table model
     */
    public MenuBar(final Font font,
                   final GuiCommandInterpreter interpreter,
                   final SpreadsheetViewTableModel spreadsheetView) {
        super();
        this.interpreter = interpreter;
        this.spreadsheetView = spreadsheetView;
        fileChooser = new FileChooser();
        cellariumFilter = new FileNameExtensionFilter(
                        "Cellarium files (*.cellarium)",
                        "cellarium");
        csvFilter = new FileNameExtensionFilter(
                        "Text files (*.csv, *.txt)",
                        "txt", "text", "csv");
        
        //Sets font
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        
        //Creation of single files
        final JMenu fileMenu = new JMenu("File");
        add(fileMenu);
        addNewMenuItem(fileMenu);
        addOpenMenuItem(fileMenu);
        addSaveMenuItem(fileMenu);
        addImportMenuItem(fileMenu);
        addExportMenuItem(fileMenu);
        addPrintMenuItem(fileMenu);
        addQuitMenuItem(fileMenu);
        
        final JMenu editMenu = new JMenu("Edit");
        add(editMenu);
        addUndoMenuItem(editMenu);
        addRedoMenuItem(editMenu);
        addClearMenuItem(editMenu);
        
        final JMenu helpMenu = new JMenu("Help");
        add(helpMenu);
        
        final JMenuItem helpItem = new JMenuItem("Cellarium Help");
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        helpItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            }
        });
        helpMenu.add(helpItem);
    }
    
    /**
     * to add NEW item to a menu.
     * @param menu the menu
     */
    private void addNewMenuItem(final JMenu menu) {
        final JMenuItem newItem = new JMenuItem("New...");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                interpreter.parseAndExecute("CLEAR", spreadsheetView);
                fileChooser.setFileFilter(cellariumFilter);
                fileChooser.saveFileDialog(interpreter, spreadsheetView);
            }
        });
        menu.add(newItem);
    }
    
    /**
     * to add OPEN item to a menu.
     * @param menu the menu
     */
    private void addOpenMenuItem(final JMenu menu) {
        final JMenuItem openItem = new JMenuItem("Open...");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                fileChooser.setFileFilter(cellariumFilter);
                fileChooser.openFileDialog(interpreter, spreadsheetView);
            }
        });
        menu.add(openItem);
    }
    
    /**
     * to add SAVE item to a menu.
     * @param menu the menu
     */
    private void addSaveMenuItem(final JMenu menu) {
        final JMenuItem saveItem = new JMenuItem("Save...");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                fileChooser.setFileFilter(cellariumFilter);
                fileChooser.saveFileDialog(interpreter, spreadsheetView);
            }
        }); 
        menu.add(saveItem);
    }
    
    /**
     * to add IMPORT item to a menu.
     * @param menu the menu
     */
    private void addImportMenuItem(final JMenu menu) {
        final JMenuItem importItem = new JMenuItem("Import...");
        importItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        importItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                fileChooser.setFileFilter(csvFilter);
                fileChooser.importFileDialog(interpreter, spreadsheetView);
            }
        });
        menu.add(importItem);
    }
    
    /**
     * to add EXPORT item to a menu.
     * @param menu the menu
     */
    private void addExportMenuItem(final JMenu menu) {
        final JMenuItem exportItem = new JMenuItem("Export...");
        exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exportItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                fileChooser.setFileFilter(csvFilter);
                fileChooser.exportFileDialog(interpreter, spreadsheetView);
            }
        }); 
        menu.add(exportItem);
    }
    
    /**
     * to add PRINT item to a menu.
     * @param menu the menu
     */
    private void addPrintMenuItem(final JMenu menu) {
        final JMenuItem printItem = new JMenuItem("Print");
        menu.add(printItem);
    }
    
    /**
     * to add QUIT item to a menu.
     * @param menu the menu
     */
    private void addQuitMenuItem(final JMenu menu) {
        final JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) { 
                System.exit(0);
            }
        });
        menu.add(quitItem);
    }
    
    /**
     * to add UNDO item to a menu.
     * @param menu the menu
     */
    private void addUndoMenuItem(final JMenu menu) {
        final JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                interpreter.parseAndExecute("UNDO", spreadsheetView);
            }
        });
        menu.add(undoItem);
    }
    
    /**
     * to add REDO item to a menu.
     * @param menu the menu
     */
    private void addRedoMenuItem(final JMenu menu) {
        final JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redoItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                interpreter.parseAndExecute("REDO", spreadsheetView);
            }
        });
        menu.add(redoItem);
    }
    
    /**
     * to add CLEAR item to a menu.
     * @param menu the menu
     */
    private void addClearMenuItem(final JMenu menu) {
        addMenuItem(menu,
                    "Clear All", 
                    KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK),
                    new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            interpreter.parseAndExecute("CLEAR", spreadsheetView);
                        }
                    });
    }
    
    private void addMenuItem(final JMenu menu, final String name, final KeyStroke keyStroke,
                            final ActionListener action) {
        final JMenuItem item = new JMenuItem(name);
        item.setAccelerator(keyStroke);
        item.addActionListener(action);
        menu.add(item);
    }
}
