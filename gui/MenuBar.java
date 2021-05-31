package gui;

import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

/**
 * The class MenuBar creates the MenuBar for the GUI of the Spreadsheet.
 *
 * @author Alessandro Gobbetti && Laurenz Ebi
 * @version 1.0
 */
public class MenuBar extends JMenuBar {
    
    private GuiCommandInterpreter interpreter;
    private SpreadsheetViewTableModel spreadsheetView;
    /**
     * Creates the a MenuBar.
     * @param font the font of the MenuBar.
     * @param interpreter the command intepreter
     * @param spreadsheetView the view table model
     */
    public MenuBar(final Font font,
                   final GuiCommandInterpreter interpreter,
                   final SpreadsheetViewTableModel spreadsheetView) {
        super();
        this.interpreter = interpreter;
        this.spreadsheetView = spreadsheetView;
        
        //Sets font
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        
        //Creation of single files
        final JMenu fileMenu = new JMenu("File");
        add(fileMenu);
        addNewMenuItem(fileMenu);
        addOpenMenuItem(fileMenu);
        addSaveMenuItem(fileMenu);
        addImportMenuItem(fileMenu);
        addExportMenuItem(fileMenu);
        addPrintMenuItem(fileMenu);
        addQuitMenuItem(fileMenu);
        
        final JMenu editMenu = new JMenu("Edit");
        add(editMenu);
        addUndoMenuItem(editMenu);
        addRedoMenuItem(editMenu);
        addClearMenuItem(editMenu);
        
        final JMenu helpMenu = new JMenu("Help");
        add(helpMenu);
        
        
        
        final JMenuItem helpItem = new JMenuItem("Cellarium Help");
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        helpItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
            }
        });
        helpMenu.add(helpItem);
    }
    
    
    private void addNewMenuItem(JMenu menu) {
        final JMenuItem newItem = new JMenuItem("New...");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
                interpreter.parseAndExecute("CLEAR", spreadsheetView);
                final FileChooser fileChooser = new FileChooser();
                FileNameExtensionFilter cellariumFilter = new FileNameExtensionFilter("Cellarium files (*.cell)", "cell");
                fileChooser.setFileFilter(cellariumFilter);
                fileChooser.openFileDialog(interpreter, spreadsheetView);
            }
        });
        menu.add(newItem);
    }
    
    private void addOpenMenuItem(JMenu menu) {
        final JMenuItem openItem = new JMenuItem("Open...");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final FileChooser fileChooser = new FileChooser();
                FileNameExtensionFilter cellariumFilter = new FileNameExtensionFilter("Cellarium files (*.cell)", "cell");
                fileChooser.setFileFilter(cellariumFilter);
                fileChooser.openFileDialog(interpreter, spreadsheetView);
            }
        });
        menu.add(openItem);
    }
    
    private void addSaveMenuItem(JMenu menu) {
        final JMenuItem saveItem = new JMenuItem("Save...");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) { 
                final FileChooser fileChooser = new FileChooser();
                FileNameExtensionFilter cellariumFilter = new FileNameExtensionFilter("Cellarium files (*.cellarium)", "cellarium");
                fileChooser.setFileFilter(cellariumFilter);
                fileChooser.saveFileDialog(interpreter, spreadsheetView);
            }
        }); 
        menu.add(saveItem);
    }
    
    private void addImportMenuItem(JMenu menu) {
        final JMenuItem importItem = new JMenuItem("Import...");
        importItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        importItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final FileChooser fileChooser = new FileChooser();
                FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Text files (*.csv, *.txt)",  "txt", "text", "csv");
                fileChooser.setFileFilter(textFilter);
                fileChooser.importFileDialog(interpreter, spreadsheetView);
            }
        });
        menu.add(importItem);
    }
    
    private void addExportMenuItem(JMenu menu) {
        final JMenuItem exportItem = new JMenuItem("Export...");
        exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        exportItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) { 
                final FileChooser fileChooser = new FileChooser();
                FileNameExtensionFilter cellariumFilter = new FileNameExtensionFilter("Text files (*.csv, *.txt)",  "txt", "text", "csv");
                fileChooser.setFileFilter(cellariumFilter);
                fileChooser.exportFileDialog(interpreter, spreadsheetView);
            }
        }); 
        menu.add(exportItem);
    }
    
    private void addPrintMenuItem(JMenu menu) {
        final JMenuItem printItem = new JMenuItem("Print");
        menu.add(printItem);
    }
    
    private void addQuitMenuItem(JMenu menu) {
        final JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) { 
                System.exit(0);
            }
        });
        menu.add(quitItem);
    }
    
    
    private void addUndoMenuItem(JMenu menu) {
        final JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
                interpreter.parseAndExecute("UNDO", spreadsheetView);
            }
        });
        menu.add(undoItem);
    }
    
    private void addRedoMenuItem(JMenu menu) {
        final JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redoItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
                interpreter.parseAndExecute("REDO", spreadsheetView);
            }
        });
        menu.add(redoItem);
    }
    
    private void addClearMenuItem(JMenu menu) {
        final JMenuItem clearItem = new JMenuItem("Clear All");
        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        clearItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //Execute when button is pressed
                interpreter.parseAndExecute("CLEAR", spreadsheetView);
            }
        });
        menu.add(clearItem);
    }
}
