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
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                interpreter.parseAndExecute("CLEAR", spreadsheetView);
                fileChooser.saveFileDialog(interpreter, spreadsheetView);
            }
        };
        addMenuItem(menu,
                    "New...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK),
                    action);
    }
    
    /**
     * to add OPEN item to a menu.
     * @param menu the menu
     */
    private void addOpenMenuItem(final JMenu menu) {
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                fileChooser.openFileDialog(interpreter, spreadsheetView);
            }
        };
        addMenuItem(menu, "Open...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK),
                    action);
    }
    
    /**
     * to add SAVE item to a menu.
     * @param menu the menu
     */
    private void addSaveMenuItem(final JMenu menu) {
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                fileChooser.saveFileDialog(interpreter, spreadsheetView);
            }
        };
        addMenuItem(menu, "Save...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK),
                    action);
    }
    
    /**
     * to add IMPORT item to a menu.
     * @param menu the menu
     */
    private void addImportMenuItem(final JMenu menu) {
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                fileChooser.importFileDialog(interpreter, spreadsheetView);
            }
        };
        addMenuItem(menu,
                    "Import...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK),
                    action);
    }
    
    /**
     * to add EXPORT item to a menu.
     * @param menu the menu
     */
    private void addExportMenuItem(final JMenu menu) {
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                fileChooser.exportFileDialog(interpreter, spreadsheetView);
            }
        };
        addMenuItem(menu,
                    "Export...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK),
                    action);
    }
    
    /**
     * to add QUIT item to a menu.
     * @param menu the menu
     */
    private void addQuitMenuItem(final JMenu menu) {
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent event) { 
                System.exit(0);
            }
        };
        addMenuItem(menu, "Quit",
                    KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK),
                    action);
    }
    
    /**
     * to add UNDO item to a menu.
     * @param menu the menu
     */
    private void addUndoMenuItem(final JMenu menu) {
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                interpreter.parseAndExecute("UNDO", spreadsheetView);
            }
        };
        addMenuItem(menu,
                    "Undo",
                    KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK),
                    action);
    }
    
    /**
     * to add REDO item to a menu.
     * @param menu the menu
     */
    private void addRedoMenuItem(final JMenu menu) {
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                interpreter.parseAndExecute("REDO", spreadsheetView);
            }
        };
        addMenuItem(menu,
                    "Redo",
                    KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK),
                    action);
    }
    
    /**
     * to add CLEAR item to a menu.
     * @param menu the menu
     */
    private void addClearMenuItem(final JMenu menu) {
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                interpreter.parseAndExecute("CLEAR", spreadsheetView);
            }
        };
        addMenuItem(menu,"Clear All", 
                    KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK),
                    action);
    }
    
    // to quickly add an item to a menu.
    private void addMenuItem(final JMenu menu,
                             final String name,
                             final KeyStroke keyStroke,
                             final ActionListener action) {
        final JMenuItem item = new JMenuItem(name);
        item.setAccelerator(keyStroke);
        item.addActionListener(action);
        menu.add(item);
    }
}
