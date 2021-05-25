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
public class ToolBar extends JPanel {
    
    /**
     * Creates the a ToolBar for the Spreadsheet.
     * @param font     defines the font of the MenuBar.
     * @param actions  Actions object to be able to compute actions.
     * @return JPanel  a new Toolbar with Buttons.
     */
    public ToolBar(final Font font, final Actions actions) {
        super();
        //Sets Font of Buttons
        UIManager.put("Button.font", font);
        
        setLayout(new FlowLayout());
        
        //Buttons
        final JButton open = new JButton("Open");
        add(open);
        final JButton save = new JButton("Save");
        add(save);
        
        final JButton up = new JButton("Up");
        up.setActionCommand("View up");
        up.addActionListener(actions);
        add(up);
        
        final JButton down = new JButton("Down");
        down.setActionCommand("View down");
        down.addActionListener(actions);
        add(down);
        
        final JButton left = new JButton("Left");
        left.setActionCommand("View left");
        left.addActionListener(actions);
        add(left);
        
        final JButton right = new JButton("Right");
        right.setActionCommand("View right");
        right.addActionListener(actions);
        add(right);
    }
}
