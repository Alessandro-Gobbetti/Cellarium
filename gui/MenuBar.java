package gui;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;


/**
 * The class MenuBar creates the MenuBar for the GUI of the Spreadsheet.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
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
        addAboutMenuItem(helpMenu);
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
    
    /**
     * to add ABOUT item to a menu.
     * @param menu the menu
     */
    private void addAboutMenuItem(final JMenu menu) {
        final ActionListener action = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFrame frame = new JFrame("About Cellarium");
                final JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
                
                JLabel picLabel = new JLabel();
                ImageIcon imageIcon = new ImageIcon(
                    new ImageIcon(
                        "gui/../CellariumIcon.png").getImage().getScaledInstance(
                            200, 200, Image.SCALE_DEFAULT));
                picLabel.setIcon(imageIcon);
                mainPanel.add(picLabel);
                
                addAboutInfo(mainPanel);
                
                frame.add(mainPanel);
                frame.setPreferredSize(new Dimension(700, 300));
                frame.setResizable(false);
                frame.pack();
                frame.setVisible(true);
            }
        };
        addMenuItem(menu,"About..", 
                    KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK),
                    action);
    }
    
    /**
     * To add the about information on a panel.
     * @param panel the panel
     */
    private void addAboutInfo(final JPanel panel) {
        final JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        
        createInfoTitleLine("Authors:", textPanel);
        createInfoLine("Alessandro Gobbetti - Laurenz Ebi", textPanel);
        createInfoLine("USI, PF2 2021, final project.", textPanel);

        textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        createInfoTitleLine("About Cellarium 1.0:", textPanel);
        createInfoLine("Visit: https://github.com/usi-pf2-2021/project-cellarium", textPanel);
        createInfoLine("To get some help read the README.", textPanel);

        panel.add(textPanel);
    }
    
    /**
     * Helper method to create a title info line.
     * @param text the string.
     * @param panel the panel to add this line. 
     */
    private void createInfoTitleLine(final String text, final JPanel panel) {
        final JTextField title = new JTextField(text);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        title.setEditable(false);
        panel.add(title);
    }
    
    /**
     * Helper method to create a info line.
     * @param text the string.
     * @param panel the panel to add this line. 
     */
    private void createInfoLine(final String text, final JPanel panel) {
        final JTextField messageField = new JTextField(text);
        messageField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        messageField.setEditable(false);
        panel.add(messageField);
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
