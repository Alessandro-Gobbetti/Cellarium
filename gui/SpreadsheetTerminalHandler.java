package gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Write a description of class SpreadsheetTerminalHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetTerminalHandler implements ActionListener {

    private SpreadsheetViewTableModel spreadsheetView;
    private GuiCommandInterpreter interpreter;
    
    public SpreadsheetTerminalHandler(final SpreadsheetViewTableModel spreadsheetView, final GuiCommandInterpreter interpreter) {
        this.interpreter = interpreter;
        this.spreadsheetView = spreadsheetView;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final String command = e.getActionCommand();
        interpreter.parseAndExecute(command, spreadsheetView);
        System.out.println(command);
    }
}
