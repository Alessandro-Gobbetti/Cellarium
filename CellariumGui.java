import java.awt.event.ActionListener;
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
import java.awt.event.ActionEvent;
/**
 * The Main GUI class.
 *
 * @author Alessandro Gobbetti & Laurenz Ebi
 * @version 1.0
 */
public class CellariumGui implements ActionListener {
    
    private Spreadsheet spreadsheet;
    private SpreadsheetViewTableModel spreadsheetView;
    
    public CellariumGui(){
        spreadsheet = new Spreadsheet();
        spreadsheetView = new SpreadsheetViewTableModel(spreadsheet);
    }
    
    public void actionPerformed(ActionEvent e) {
        if ("View up".equals(e.getActionCommand())) {
            int row = spreadsheetView.getOriginRow();
            if (row > 1) {
                spreadsheetView.setOrigin(row - 1, spreadsheetView.getOriginCol());
            }
        } else if ("View down".equals(e.getActionCommand())) {
            int row = spreadsheetView.getOriginRow();
            spreadsheetView.setOrigin(row + 1, spreadsheetView.getOriginCol());
        } else if ("View left".equals(e.getActionCommand())) {
            int col = spreadsheetView.getOriginCol();
            if (col > 1) {
                spreadsheetView.setOrigin(spreadsheetView.getOriginRow(), col - 1);
            }
        } else if ("View right".equals(e.getActionCommand())) {
            int col = spreadsheetView.getOriginCol();
            spreadsheetView.setOrigin(spreadsheetView.getOriginRow(), col + 1);
        }
    } 
    
    public void run() {
        //Font style
        final Font font = new Font("SansSerif", Font.PLAIN, 14);
        
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
        
        final JButton up = new JButton("Up");
        //up.setMnemonic(KeyEvent.VK_SCROLL_LOCK);
        up.setActionCommand("View up");
        //up.setEnabled(false);
        up.addActionListener(this);
        toolbarPanel.add(up);
        
        final JButton down = new JButton("Down");
        down.setActionCommand("View down");
        down.addActionListener(this);
        toolbarPanel.add(down);
        
        final JButton left = new JButton("Left");
        left.setActionCommand("View left");
        left.addActionListener(this);
        toolbarPanel.add(left);
        
        final JButton right = new JButton("Right");
        right.setActionCommand("View right");
        right.addActionListener(this);
        toolbarPanel.add(right);
        
        
        
        topPanel.add(toolbarPanel);
        
        // ExpressionField
        final JTextField expressionField = new JTextField(); // do something
        topPanel.add(expressionField);
        
        frame.add(topPanel, BorderLayout.NORTH);
        
        
        // Spreadsheet
        final JPanel spreadsheetPanel = new JPanel(new SpringLayout());
        //final Table table = new Table(new Spreadsheet());
        //table.editTable();
        //spreadsheetPanel.add(table);
        
        final JTable table = new JTable(spreadsheetView);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        
        table.setDefaultRenderer(String.class, new SpreadsheetViewTableRenderer());
        spreadsheetPanel.add(table);
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

    /**
     * Main function to start GUI.
     * @param args  String[] of commands.
     */
    public static void main(final String[] args) {
        CellariumGui gui = new CellariumGui();
        gui.run();
    }

}
