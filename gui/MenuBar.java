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
        addMenuItem(fileMenu, "New...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK),
                    actionNew());
        addMenuItem(fileMenu, "Open...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK),
                    actionOpen());
        addMenuItem(fileMenu, "Save...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK),
                    actionSave());
        addMenuItem(fileMenu,
                    "Import...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK),
                    actionImport());
        addMenuItem(fileMenu,
                    "Export...",
                    KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK),
                    actionExport());
        addMenuItem(fileMenu, "Quit",
                    KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK),
                    actionQuit());
        
        final JMenu editMenu = new JMenu("Edit");
        add(editMenu);
        addMenuItem(editMenu,
                    "Undo",
                    KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK),
                    actionUndo());
        addMenuItem(editMenu,
                    "Redo",
                    KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK),
                    actionRedo());
        addMenuItem(editMenu,"Clear All", 
                    KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK),
                    actionClear());
        
        final JMenu helpMenu = new JMenu("Help");
        add(helpMenu);
        addMenuItem(helpMenu,"About..", 
                    KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK),
                    actionAbout());
    }
    
    
    /**
     * Action for creating a new empty spreadsheet.
     * @return the action for new.
     */
    private ActionListener actionNew() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                interpreter.parseAndExecute("CLEAR", spreadsheetView);
                fileChooser.saveFileDialog(interpreter, spreadsheetView);
            }
        };
    }
    
    /**
     * Action to open a file.
     * @return the action for open.
     */
    private ActionListener actionOpen() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                fileChooser.openFileDialog(interpreter, spreadsheetView);
            }
        };
    }
    
    /**
     * Action to save a file.
     * @return the action for save.
     */
    private ActionListener actionSave() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                fileChooser.saveFileDialog(interpreter, spreadsheetView);
            }
        };
    }
    
    /**
     * Action to import a file.
     * @return the action for import.
     */
    private ActionListener actionImport() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                fileChooser.importFileDialog(interpreter, spreadsheetView);
            }
        };
    }
    
    /**
     * Action to export a file.
     * @return the action for export.
     */
    private ActionListener actionExport() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                fileChooser.exportFileDialog(interpreter, spreadsheetView);
            }
        };
    }
    
    /**
     * Action to quit the program.
     * @return the action for quit.
     */
    private ActionListener actionQuit() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                System.exit(0);
            }
        };
    }
    
    /**
     * Action to undo last command.
     * @return the action for undo.
     */
    private ActionListener actionUndo() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                interpreter.parseAndExecute("UNDO", spreadsheetView);
            }
        };
    }
    
    /**
     * Action to redo last command.
     * @return the action for redo.
     */
    private ActionListener actionRedo() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                interpreter.parseAndExecute("REDO", spreadsheetView);
            }
        };
    }
    
    /**
     * Action to clear the spreadsheet.
     * @return the action for clear.
     */
    private ActionListener actionClear() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                interpreter.parseAndExecute("CLEAR", spreadsheetView);
            }
        };
    }
    
    /**
     * Action to open an about window.
     * @return the action for about.
     */
    private ActionListener actionAbout() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                final JFrame frame = new JFrame("About Cellarium");
                final JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
                
                final JLabel picLabel = new JLabel();
                final ImageIcon imageIcon = new ImageIcon(
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
