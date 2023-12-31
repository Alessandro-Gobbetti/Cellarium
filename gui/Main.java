package gui;

import spreadsheet.Spreadsheet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Scrollbar;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


/**
 * The Main GUI class.
 *
 * @author Alessandro Gobbetti - Laurenz Ebi
 * @version 1.0
 */
public class Main {
    
    private Spreadsheet spreadsheet;
    private GuiCommandInterpreter interpreter;
    private SpreadsheetViewTableModel spreadsheetView;
    
    private static final Font FONT = new Font("SansSerif", Font.PLAIN, 14);
    
    /**
     * Constructor for CellariumGui.
     */
    public Main() {
        spreadsheet = new Spreadsheet();
        interpreter = new GuiCommandInterpreter();
        spreadsheetView = new SpreadsheetViewTableModel(spreadsheet, interpreter);
    }
    
    /**
     * Main function to start GUI.
     * @param args  String[] of commands.
     */
    public static void main(final String[] args) {
        final Main gui = new Main();
        gui.run();
    }
    
    /**
     * To draw and run the gui.
     */
    public void run() {
        //Frame
        final JFrame frame = new JFrame("Cellarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Menu Bar 
        frame.setJMenuBar(new MenuBar(FONT, interpreter, spreadsheetView));
        
        //MainPanel
        final JPanel mainPanel = new JPanel(new BorderLayout());
        
        //TopPanel
        final JPanel topPanel = new JPanel(new BorderLayout());
        
        //Spreadsheet
        final JPanel spreadsheetPanel = new JPanel(new SpringLayout());
        
        //Table 
        final Table table = new Table(spreadsheetView);
        //Adds Table to Spreadsheet
        spreadsheetPanel.add(table);
        
        //Adds Spreadsheet to mainPanel
        mainPanel.add(spreadsheetPanel, BorderLayout.CENTER);
        
        // to init the top panel, with a terminal and a message field.
        initTopPanel(table, topPanel);
        
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

        //Set window dimensions
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setMinimumSize(new Dimension(300, 300));
        
        //add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        frame.pack();
        // to init the scrollbars (here because they need to know the dimensions)
        rowScrollbarHandler.init();
        colScrollbarHandler.init();
        frame.setVisible(true);
    }
    
    /**
     * To init the terminal.
     * 
     * @param table the table this panel is connected to.
     * @param panel the panel.
     */
    private void initTopPanel(final Table table, final JPanel panel) {
        final String currentFormula = table.getSelectedCell(spreadsheet).getFormula();
        final JTextField terminal = new JTextField(currentFormula);
        terminal.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        terminal.setFont(new Font("SansSerif", Font.PLAIN, 16));
        final SpreadsheetTerminalHandler spreadsheetTerminalHandler = 
            new SpreadsheetTerminalHandler(spreadsheetView, interpreter);
        terminal.addActionListener(spreadsheetTerminalHandler);
        //Adds the expressionField to the topPanel
        panel.add(terminal, BorderLayout.NORTH);
        
        //Message
        final JTextField messageField = new JTextField();
        interpreter.setOutputMessageField(messageField);
        messageField.setEditable(false);
        panel.add(messageField, BorderLayout.SOUTH);
        
        final SpreadsheetMouseHandler spreadsheetMouseHandler = 
            new SpreadsheetMouseHandler(spreadsheetView, terminal, messageField, table);
        table.addMouseListener(spreadsheetMouseHandler);
    }
}
