package gui;

import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.event.ActionListener;

/**
 * The class MenuBar creates the MenuBar for the GUI of the Spreadsheet.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class MenuBar extends JMenuBar {
    
    /**
     * Creates the a MenuBar.
     * @param font       defines the font of the MenuBar.
     */
    public MenuBar(final Font font) {
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
            public void actionPerformed(ActionEvent e) {
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
        editMenu.add(saveItem);
        final JMenuItem deliteAllItem = new JMenuItem("Delite All");
        editMenu.add(deliteAllItem);
        final JMenuItem helpItem = new JMenuItem("Cellarium Help");
        helpMenu.add(helpItem);
    }
}
