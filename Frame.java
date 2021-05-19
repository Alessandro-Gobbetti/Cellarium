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

/**
 * The Main GUI class.
 *
 * @author (Alessandro Gobbetti & Laurenz Ebi)
 * @version (1.0)
 */
public class Frame {

    /**
     * Main function to start GUI.
     */
    public static void main(String[] args) {
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
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new FlowLayout());
        
        //Table
        //JTable spreadsheet = new JTable();
        //mainPanel.add(spreadsheet);
        
        //Buttons
        JButton open = new JButton("Open");
        toolbarPanel.add(open);
        JButton save = new JButton("Save");
        toolbarPanel.add(save);
        
        mainPanel.add(toolbarPanel, BorderLayout.NORTH);
        
        //Font
        //Font newFont = new Font("Verdana", 2, 20);
        //frame.setFont(newFont);
        
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
