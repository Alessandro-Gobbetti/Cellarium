package gui;

import spreadsheet.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


/**
 * The Main GUI class.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class CellariumGui {
    
    private Spreadsheet spreadsheet;
    private SpreadsheetViewTableModel spreadsheetView;
    
    /**
     * Constructor for CellariumGui.
     */
    public CellariumGui() {
        spreadsheet = new Spreadsheet();
        spreadsheetView = new SpreadsheetViewTableModel(spreadsheet);
    }
    
    /**
     * Main function to start GUI.
     * @param args  String[] of commands.
     */
    public static void main(final String[] args) {
        final CellariumGui gui = new CellariumGui();
        gui.run();
    }
    
    /**
     * To draw the gui.
     */
    public void run() {
        //Font style
        final Font font = new Font("SansSerif", Font.PLAIN, 14);
        
        //Frame
        final JFrame frame = new JFrame("Cellarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Menu Bar 
        final MenuBar menubar = new MenuBar();
        //Adds MenuBar to frame
        frame.setJMenuBar(menubar.createMenuBar(font));
        
        
        //MainPanel
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        //TopPanel
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));

        
        //ToolBar
        final ToolBar toolbar = new ToolBar();
        //Makes possible that actions are executed on the ToolBar
        final Actions actions = new Actions(spreadsheet, spreadsheetView);
        
        //Adds ToolBar to TopPanel
        topPanel.add(toolbar.createToolBar(font, actions));
        
        
        //ExpressionField
        final JTextField expressionField = new JTextField(); // do something
        //Adds the expressionField to the topPanel
        topPanel.add(expressionField);
        
        //Adds TopPanel to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        
        
        //Spreadsheet
        final JPanel spreadsheetPanel = new JPanel(new SpringLayout());
        
        //Table 
        final Table table = new Table(); 
       
        //Adds Table to Spreadsheet
        spreadsheetPanel.add(table.createTable(spreadsheetView));
        
        //Adds Spreadsheet to mainPanel
        mainPanel.add(spreadsheetPanel, BorderLayout.CENTER);
        
        
        //Set window dimensions
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setMinimumSize(new Dimension(300, 300));
        
        //Adds mainPanel to frame
        frame.add(mainPanel);
        
        frame.pack();
        frame.setVisible(true);
    }

}
