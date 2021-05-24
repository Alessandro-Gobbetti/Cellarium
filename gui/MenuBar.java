package gui;

import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

/**
 * The class MenuBar creates the MenuBar for the GUI of the Spreadsheet.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class MenuBar {
    
    /**
     * Creates the a JMenuBar.
     * @param font       defines the font of the MenuBar.
     * @return JMenuBar  a new JMenuBar.
     */
    public JMenuBar createMenuBar(final Font font) {
        //Sets font
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        
        //Creation of JMenuBar
        final JMenuBar menubar = new JMenuBar();
        
        //Creation of single files
        final JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        final JMenu editMenu = new JMenu("Edit");
        menubar.add(editMenu);
        final JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);
        
        //Definition of items in files
        final JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        final JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
        final JMenuItem printItem = new JMenuItem("Print");
        fileMenu.add(printItem);
        final JMenuItem saveItem = new JMenuItem("Save");
        editMenu.add(saveItem);
        final JMenuItem deliteAllItem = new JMenuItem("Delite All");
        editMenu.add(deliteAllItem);
        final JMenuItem helpItem = new JMenuItem("Cellarium Help");
        helpMenu.add(helpItem);
        
        //Returns the created JMenuBar
        return menubar;
    }
}
