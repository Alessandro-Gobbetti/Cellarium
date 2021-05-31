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
        //Sets font
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        
        //Creation of single files
        final JMenu fileMenu = new JMenu("File");
        add(fileMenu);
        final JMenu editMenu = new JMenu("Edit");
        add(editMenu);
        final JMenu helpMenu = new JMenu("Help");
        add(helpMenu);
        
        //Definition of items in files
        final JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
                
                System.out.println("You clicked the button");
            }
        });  
        fileMenu.add(openItem);
        final JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
        final JMenuItem printItem = new JMenuItem("Print");
        fileMenu.add(printItem);
        final JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        
        // UNDO
        final JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
                interpreter.parseAndExecute("UNDO", spreadsheetView);
            }
        });
        editMenu.add(undoItem);
        
        // REDO
        final JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redoItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
                interpreter.parseAndExecute("REDO", spreadsheetView);
            }
        });
        editMenu.add(redoItem);
        
        // CLEAR
        final JMenuItem clearItem = new JMenuItem("Clear All");
        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        clearItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
                interpreter.parseAndExecute("CLEAR", spreadsheetView);
            }
        });
        editMenu.add(clearItem);
        
        final JMenuItem helpItem = new JMenuItem("Cellarium Help");
        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        clearItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
            }
        });
        helpMenu.add(helpItem);
    }
}
