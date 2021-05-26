package gui;

import spreadsheet.Spreadsheet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        final MenuBar menubar = new MenuBar(font);
        //Adds MenuBar to frame
        frame.setJMenuBar(menubar);
        
        
        //MainPanel
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        //TopPanel
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));

        
        //ToolBar
        //Makes possible that actions are executed on the ToolBar
        final Actions actions = new Actions(spreadsheetView);
        final ToolBar toolbar = new ToolBar(font, actions);
        //Adds ToolBar to TopPanel
        topPanel.add(toolbar);
        //table.getSelected
        
        
        
        
        //Spreadsheet
        final JPanel spreadsheetPanel = new JPanel(new SpringLayout());
        
        //Table 
        final Table table = new Table(spreadsheetView);
       
        //Adds Table to Spreadsheet
        spreadsheetPanel.add(table);
        
        //Adds Spreadsheet to mainPanel
        mainPanel.add(spreadsheetPanel, BorderLayout.CENTER);
        

        
        //ExpressionField
        final String currentFormula = table.getSelectedCell(spreadsheet).getFormula();
        final JTextField expressionField = new JTextField(currentFormula);
        //Adds the expressionField to the topPanel
        topPanel.add(expressionField);
        
        //Adds TopPanel to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        
        
        // Srollbars
        final Scrollbar rowScrollbar = new Scrollbar(Scrollbar.VERTICAL);
        final SpreadsheetScrollbarHandler rowScrollbarHandler =
                new SpreadsheetScrollbarHandler(spreadsheetView, rowScrollbar, table);
        rowScrollbar.addAdjustmentListener(rowScrollbarHandler);
        mainPanel.add(rowScrollbar, BorderLayout.EAST);
        
        final Scrollbar colScrollbar = new Scrollbar(Scrollbar.HORIZONTAL);
        final SpreadsheetScrollbarHandler colScrollbarHandler =
            new SpreadsheetScrollbarHandler(spreadsheetView, colScrollbar, table);
        colScrollbar.addAdjustmentListener(colScrollbarHandler);
        mainPanel.add(colScrollbar, BorderLayout.SOUTH);
        
        
        
        
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                final int row = spreadsheetView.viewToSpreadsheetRow(
                                    table.rowAtPoint(e.getPoint()));
                final int col = spreadsheetView.viewToSpreadsheetCol(
                                    table.columnAtPoint(e.getPoint()));
                //FIXME
                //currentFormula = spreadsheet.getFormula(row,col);
                System.out.println("Formula in this cell: " + spreadsheet.getFormula(row,col));
            }

        });
        
        
        
        
        
        //Set window dimensions
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setMinimumSize(new Dimension(300, 300));
        
        //Adds mainPanel to frame
        frame.add(mainPanel);
        
        frame.pack();
        rowScrollbarHandler.init();//initHorizontalScrollbar(horizontalScrollbar, table);
        colScrollbarHandler.init();
        frame.setVisible(true);
    }
    
}