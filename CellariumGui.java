import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

/**
 * The Main GUI class.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class CellariumGui {

    /**
     * Main function to start GUI.
     * @param args  String[] of commands.
     */
    public static void main(final String[] args) {
        //Font style
        final Font font = new Font("sans serif", Font.PLAIN, 14);
        
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("Button.font", font);

        // Frame
        final JFrame frame = new JFrame("Cellarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //MenuBar
        final JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        final JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        final JMenu editMenu = new JMenu("Edit");
        menubar.add(editMenu);
        final JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);
        
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
        
        
        
        //Panels
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        
        final JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new FlowLayout());
        
        //Buttons
        final JButton open = new JButton("Open");
        toolbarPanel.add(open);
        final JButton save = new JButton("Save");
        toolbarPanel.add(save);
        
        topPanel.add(toolbarPanel);
        
        // ExpressionField
        final JTextField expressionField = new JTextField(); // do something
        topPanel.add(expressionField);
        
        frame.add(topPanel, BorderLayout.NORTH);
        
        
        // Spreadsheet
        final JPanel spreadsheetPanel = new JPanel(new SpringLayout());
        spreadsheetPanel.add(new JTable(60,30));
        mainPanel.add(spreadsheetPanel, BorderLayout.CENTER);
        
        // set window dimensions
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setMinimumSize(new Dimension(300, 300));
        
        //Font
        //Font newFont = new Font("Verdana", 2, 20);
        //frame.setFont(newFont);
        
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
