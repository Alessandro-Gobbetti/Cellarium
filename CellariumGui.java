import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.UIManager;

/**
 * The Main GUI class.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public class CellariumGui {

    /**
     * Main function to start GUI.
     */
    public static void main(String[] args) {
        //Font style
        Font font = new Font("calibri", Font.PLAIN, 14);
        
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("Button.font", font);

        // Frame
        JFrame frame = new JFrame("Cellarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //MenuBar
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        JMenu editMenu = new JMenu("Edit");
        menubar.add(editMenu);
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);
        
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
        JMenuItem printItem = new JMenuItem("Print");
        fileMenu.add(printItem);
        JMenuItem saveItem = new JMenuItem("Save");
        editMenu.add(saveItem);
        JMenuItem deliteAllItem = new JMenuItem("Delite All");
        editMenu.add(deliteAllItem);
        JMenuItem helpItem = new JMenuItem("Cellarium Help");
        helpMenu.add(helpItem);
        
        
        
        //Panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new FlowLayout());
        
        //Buttons
        JButton open = new JButton("Open");
        toolbarPanel.add(open);
        JButton save = new JButton("Save");
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
