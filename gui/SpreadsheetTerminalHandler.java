package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class SpreadsheetTerminalHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetTerminalHandler implements ActionListener {

    private SpreadsheetViewTableModel spreadsheetView;
    private GuiCommandInterpreter interpreter;
    
    /**
     * Creator for SpreadsheetTerminalHandler.
     * 
     * @param spreadsheetView the table model
     * @param interpreter the interpreter
     */
    public SpreadsheetTerminalHandler(final SpreadsheetViewTableModel spreadsheetView,
                                      final GuiCommandInterpreter interpreter) {
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
