package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Creates a ToolBar for the Spreadsheet.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class ToolBar {
    
    /**
     * Creates the a ToolBar for the Spreadsheet.
     * @param font     defines the font of the MenuBar.
     * @return JPanel  a new Toolbar with Buttons.
     */
    public JPanel createToolBar(final Font font, final Actions actions) {
        //Sets Font of Buttons
        UIManager.put("Button.font", font);
        
        //Creates JPanel for ToolBar
        final JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new FlowLayout());
        
        //Buttons
        final JButton open = new JButton("Open");
        toolbarPanel.add(open);
        final JButton save = new JButton("Save");
        toolbarPanel.add(save);
        
        final JButton up = new JButton("Up");
        up.setActionCommand("View up");
        up.addActionListener(actions);
        toolbarPanel.add(up);
        
        final JButton down = new JButton("Down");
        down.setActionCommand("View down");
        down.addActionListener(actions);
        toolbarPanel.add(down);
        
        final JButton left = new JButton("Left");
        left.setActionCommand("View left");
        left.addActionListener(actions);
        toolbarPanel.add(left);
        
        final JButton right = new JButton("Right");
        right.setActionCommand("View right");
        right.addActionListener(actions);
        toolbarPanel.add(right);
        
        //Returns toolbar
        return toolbarPanel;
    }
}
